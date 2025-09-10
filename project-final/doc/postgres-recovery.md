# PostgreSQL startup failure: could not locate a valid checkpoint record

If you see logs like:

```
PANIC:  could not locate a valid checkpoint record at ...
LOG:  invalid checkpoint record / invalid resource manager ID in checkpoint record
```

This usually means the data directory (PGDATA) is corrupted or incompatible with the running PostgreSQL version (often after upgrading the image or an unclean shutdown).

## Quick fixes for local/dev

- If you used the docker command from `doc/postgres-docker.md` with a bind mount `-v ./pgdata:/var/lib/postgresql/data`, your database files live under the project directory. For development, it is safe to remove them and let Postgres re-initialize:
  
  ```bash
  # Stop the container first
  docker stop postgres-db && docker rm postgres-db
  # Remove local data directory (DEV ONLY)
  rm -rf ./pgdata
  # Recreate the container (will initialize a fresh cluster)
  docker run -p 5432:5432 --name postgres-db \
    -e POSTGRES_USER=jira -e POSTGRES_PASSWORD=CodeGymJira -e POSTGRES_DB=jira \
    -e PGDATA=/var/lib/postgresql/data/pgdata \
    -v ./pgdata:/var/lib/postgresql/data -d postgres:17
  ```

- Prefer a named Docker volume to avoid committing PGDATA into the repo and reduce corruption risk on host restarts:
  
  ```bash
  docker volume create jira_pgdata
  docker run -p 5432:5432 --name postgres-db \
    -e POSTGRES_USER=jira -e POSTGRES_PASSWORD=CodeGymJira -e POSTGRES_DB=jira \
    -e PGDATA=/var/lib/postgresql/data/pgdata \
    -v jira_pgdata:/var/lib/postgresql/data -d postgres:17
  ```

- Ensure the Postgres image version matches your data directory. If the data was created with a different major version, upgrade with `pg_upgrade` or recreate the data dir.

## Tests: use ephemeral PostgreSQL with Testcontainers

To avoid relying on any local Postgres instance during tests, this project is configured to spin up an ephemeral PostgreSQL container automatically. The test profile uses the Testcontainers JDBC URL:

```
spring.datasource.url=jdbc:tc:postgresql:17:///jira-test
```

This ensures tests do not reuse any on-disk data and are isolated and reproducible.

## Advanced (not recommended for prod): `pg_resetwal`

For disposable dev environments, you can attempt to salvage a cluster using `pg_resetwal`, but this can lose transactions and should not be used for any data you care about. Prefer re-initializing your data directory for dev.

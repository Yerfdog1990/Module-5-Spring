package jdbc.rowmapper;

import jdbc.batchinsert.PersonEntity;

import java.util.List;

public interface IPersonMapperRepository {
    List<PersonEntity> findAll();

    List<PersonEntity> findByName(String name);

    int[] batchInsert(List<PersonEntity> people);
}

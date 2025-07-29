package jdbc.batchinsert;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface IPersonBatchRepository {
    int[] batchInsert(List<PersonEntity> people);
}

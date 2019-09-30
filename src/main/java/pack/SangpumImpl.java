package pack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class SangpumImpl extends JdbcDaoSupport implements SangpumInter {
	// init 주석처리 된 곳 사용할 때
//	public SangpumImpl() {
//	}
	
	// init 주석처리 후
	public SangpumImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	public List<SangpumDto> selectList() throws DataAccessException {
		RowMapper rowMapper = new SangpumRowMapper();
		
		return getJdbcTemplate().query("select * from sangdata", rowMapper);
	}
	
	// Inner Class
	class SangpumRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// select 실행 결과를 한 레코드씩 전달받아 처리
			System.out.println("rowNum : " + rowNum);
			SangpumDto dto = new SangpumDto();
			dto.setCode(rs.getString("code"));
			dto.setSang(rs.getString("sang"));
			dto.setSu(rs.getString("su"));
			dto.setDan(rs.getString("dan"));
			
			return dto;
		}
	}
}
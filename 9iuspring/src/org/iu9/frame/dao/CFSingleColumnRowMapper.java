package org.iu9.frame.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.dao.TypeMismatchDataAccessException;
import org.springframework.jdbc.core.SingleColumnRowMapper;
/**
 * 映射简单类型
 * @author caomei
 * @param <T>
 */
public class CFSingleColumnRowMapper<T> extends SingleColumnRowMapper<T>{
	private Class<T> requiredType;
	
	public CFSingleColumnRowMapper(){
		
	}
	
	public CFSingleColumnRowMapper (Class<T> requiredType) {
		this.requiredType = requiredType;
	}
	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {

		// Validate column count.
		ResultSetMetaData rsmd = rs.getMetaData();
		int nrOfColumns = rsmd.getColumnCount();
		/*
		if (nrOfColumns != 1) {
			throw new IncorrectResultSetColumnCountException(1, nrOfColumns);
		}
       */
		// Extract column value from JDBC ResultSet.
		int columnIndex=1;
		String columnName=rsmd.getColumnName(columnIndex);
		if("frame_page_sql_row_number".equals(columnName.toLowerCase())){
			columnIndex=2;
		}
		
		Object result = getColumnValue(rs,columnIndex, this.requiredType);
		if (result != null && this.requiredType != null && !this.requiredType.isInstance(result)) {
			// Extracted value does not match already: try to convert it.
			try {
				return (T) convertValueToRequiredType(result, this.requiredType);
			}
			catch (IllegalArgumentException ex) {
				throw new TypeMismatchDataAccessException(
						"Type mismatch affecting row number " + rowNum + " and column type '" +
						rsmd.getColumnTypeName(1) + "': " + ex.getMessage());
			}
		}
		return (T) result;
	
	}

}

package com.Playwi0.Mapper;

import java.sql.ResultSet;

public interface RowMapper {

    Object mapRow(ResultSet rs);

}

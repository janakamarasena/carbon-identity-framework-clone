/*
 * Copyright (c) 2020-2024, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.role.mgt.core.dao;

/**
 * SQL Queries used in {@link RoleDAOImpl}.
 */
public class SQLQueries {

    public static final String ADD_ROLE_SQL = "INSERT INTO UM_HYBRID_ROLE (UM_ROLE_NAME, UM_TENANT_ID) VALUES "
            + "(:UM_ROLE_NAME;, :UM_TENANT_ID;)";
    public static final String ADD_ROLE_WITH_UUID_SQL = "INSERT INTO UM_HYBRID_ROLE (UM_ROLE_NAME, UM_TENANT_ID, " +
            "UM_UUID) VALUES (:UM_ROLE_NAME;, :UM_TENANT_ID;, :UM_UUID;)";

    public static final String DELETE_ROLE_SQL = "DELETE FROM UM_HYBRID_ROLE WHERE UM_ROLE_NAME=:UM_ROLE_NAME; AND "
            + "UM_TENANT_ID=:UM_TENANT_ID;";

    public static final String IS_ROLE_EXIST_SQL = "SELECT COUNT(UM_ID) FROM UM_HYBRID_ROLE WHERE UM_ROLE_NAME "
            + "=:UM_ROLE_NAME; AND UM_TENANT_ID=:UM_TENANT_ID;";

    public static final String UPDATE_ROLE_NAME_SQL = "UPDATE UM_HYBRID_ROLE SET UM_ROLE_NAME=:NEW_UM_ROLE_NAME; WHERE "
            + "UM_ROLE_NAME=:UM_ROLE_NAME; AND UM_TENANT_ID=:UM_TENANT_ID;";

    public static final String UPDATE_HYBRID_ROLE_UUID_SQL = "UPDATE UM_HYBRID_ROLE SET UM_UUID=:UM_UUID; WHERE " +
            "UM_ROLE_NAME=:UM_ROLE_NAME; AND UM_TENANT_ID=:UM_TENANT_ID;";

    public static final String ADD_USER_TO_ROLE_SQL = "INSERT INTO UM_HYBRID_USER_ROLE (UM_USER_NAME, UM_ROLE_ID, "
            + "UM_TENANT_ID, UM_DOMAIN_ID) VALUES (:UM_USER_NAME;,(SELECT UM_ID FROM UM_HYBRID_ROLE WHERE "
            + "UM_ROLE_NAME=:UM_ROLE_NAME; AND UM_TENANT_ID=:UM_TENANT_ID; LIMIT 1), :UM_TENANT_ID;, (SELECT "
            + "UM_DOMAIN_ID FROM UM_DOMAIN WHERE UM_TENANT_ID=:UM_TENANT_ID; AND UM_DOMAIN_NAME=:UM_DOMAIN_NAME;))";

    public static final String ADD_USER_TO_ROLE_SQL_MSSQL =
            "INSERT INTO UM_HYBRID_USER_ROLE (UM_USER_NAME, UM_ROLE_ID, "
                    + "UM_TENANT_ID,  UM_DOMAIN_ID) SELECT (:UM_USER_NAME;),(SELECT TOP 1 UM_ID FROM UM_HYBRID_ROLE "
                    + "WHERE UM_ROLE_NAME=:UM_ROLE_NAME; AND UM_TENANT_ID=:UM_TENANT_ID;), (:UM_TENANT_ID;), "
                    + "(SELECT UM_DOMAIN_ID FROM UM_DOMAIN WHERE UM_TENANT_ID=:UM_TENANT_ID; AND "
                    + "UM_DOMAIN_NAME=:UM_DOMAIN_NAME;)";

    public static final String REMOVE_USER_FROM_ROLE_SQL =
            "DELETE FROM UM_HYBRID_USER_ROLE WHERE UM_USER_NAME=:UM_USER_NAME; AND "
                    + "UM_ROLE_ID=(SELECT UM_ID FROM UM_HYBRID_ROLE WHERE UM_ROLE_NAME=:UM_ROLE_NAME; AND "
                    + "UM_TENANT_ID=:UM_TENANT_ID; LIMIT 1) AND UM_TENANT_ID=:UM_TENANT_ID; AND UM_DOMAIN_ID=(SELECT "
                    + "UM_DOMAIN_ID FROM UM_DOMAIN WHERE "
                    + "UM_TENANT_ID=:UM_TENANT_ID; AND UM_DOMAIN_NAME=:UM_DOMAIN_NAME;)";
    public static final String REMOVE_USER_FROM_ROLE_SQL_MSSQL =
            "DELETE FROM UM_HYBRID_USER_ROLE WHERE UM_USER_NAME=:UM_USER_NAME; AND "
                    + "UM_ROLE_ID=(SELECT TOP 1 UM_ID FROM UM_HYBRID_ROLE WHERE UM_ROLE_NAME=:UM_ROLE_NAME; AND "
                    + "UM_TENANT_ID=:UM_TENANT_ID;) AND UM_TENANT_ID=:UM_TENANT_ID; AND UM_DOMAIN_ID=(SELECT "
                    + "UM_DOMAIN_ID FROM UM_DOMAIN WHERE "
                    + "UM_TENANT_ID=:UM_TENANT_ID; AND UM_DOMAIN_NAME=:UM_DOMAIN_NAME;)";

    public static final String ADD_GROUP_TO_ROLE_SQL = "INSERT INTO UM_HYBRID_GROUP_ROLE (UM_GROUP_NAME, UM_ROLE_ID, "
            + "UM_TENANT_ID, UM_DOMAIN_ID) VALUES (:UM_GROUP_NAME;,(SELECT UM_ID FROM UM_HYBRID_ROLE WHERE "
            + "UM_ROLE_NAME=:UM_ROLE_NAME; AND UM_TENANT_ID=:UM_TENANT_ID;), :UM_TENANT_ID;, (SELECT UM_DOMAIN_ID "
            + "FROM UM_DOMAIN WHERE UM_TENANT_ID=:UM_TENANT_ID; AND UM_DOMAIN_NAME=:UM_DOMAIN_NAME;))";

    public static final String ADD_GROUP_TO_ROLE_SQL_MSSQL =
            "INSERT INTO UM_HYBRID_GROUP_ROLE (UM_GROUP_NAME, UM_ROLE_ID, "
                    + "UM_TENANT_ID,  UM_DOMAIN_ID) SELECT (:UM_GROUP_NAME;),(SELECT UM_ID FROM UM_HYBRID_ROLE WHERE "
                    + "UM_ROLE_NAME=:UM_ROLE_NAME; AND UM_TENANT_ID=:UM_TENANT_ID;), (:UM_TENANT_ID;), "
                    + "(SELECT UM_DOMAIN_ID FROM UM_DOMAIN WHERE UM_TENANT_ID=:UM_TENANT_ID; AND "
                    + "UM_DOMAIN_NAME=:UM_DOMAIN_NAME;)";

    public static final String REMOVE_GROUP_FROM_ROLE_SQL =
            "DELETE FROM UM_HYBRID_GROUP_ROLE WHERE " + "UM_GROUP_NAME=:UM_GROUP_NAME; AND "
                    + "UM_ROLE_ID=(SELECT UM_ID FROM UM_HYBRID_ROLE WHERE UM_ROLE_NAME=:UM_ROLE_NAME; AND "
                    + "UM_TENANT_ID=:UM_TENANT_ID;) AND UM_TENANT_ID=:UM_TENANT_ID; AND UM_DOMAIN_ID=(SELECT "
                    + "UM_DOMAIN_ID FROM UM_DOMAIN WHERE "
                    + "UM_TENANT_ID=:UM_TENANT_ID; AND UM_DOMAIN_NAME=:UM_DOMAIN_NAME;)";

    public static final String DELETE_USER_SQL = "DELETE FROM UM_HYBRID_USER_ROLE WHERE UM_USER_NAME=:UM_USER_NAME; "
            + "AND UM_TENANT_ID=:UM_TENANT_ID; AND UM_DOMAIN_ID=(SELECT UM_DOMAIN_ID FROM UM_DOMAIN WHERE "
            + "UM_TENANT_ID=:UM_TENANT_ID; AND UM_DOMAIN_NAME=:UM_DOMAIN_NAME;)";

    public static final String DELETE_GROUP_SQL =
            "DELETE FROM UM_HYBRID_GROUP_ROLE WHERE UM_GROUP_NAME=:UM_GROUP_NAME; "
                    + "AND UM_TENANT_ID=:UM_TENANT_ID; AND UM_DOMAIN_ID=(SELECT UM_DOMAIN_ID FROM UM_DOMAIN WHERE "
                    + "UM_TENANT_ID=:UM_TENANT_ID; AND UM_DOMAIN_NAME=:UM_DOMAIN_NAME;)";

    public static final String GET_USER_LIST_OF_ROLE_SQL =
            "SELECT UM_USER_NAME, UM_DOMAIN_NAME FROM UM_HYBRID_USER_ROLE, UM_DOMAIN WHERE "
                    + "UM_ROLE_ID=(SELECT UM_ID FROM UM_HYBRID_ROLE WHERE UM_ROLE_NAME=:UM_ROLE_NAME; AND "
                    + "UM_TENANT_ID=:UM_TENANT_ID; LIMIT 1) AND UM_HYBRID_USER_ROLE.UM_TENANT_ID=:UM_TENANT_ID; "
                    + "AND UM_HYBRID_USER_ROLE.UM_DOMAIN_ID=UM_DOMAIN.UM_DOMAIN_ID";
    public static final String GET_USER_LIST_OF_ROLE_SQL_MSSQL =
            "SELECT UM_USER_NAME, UM_DOMAIN_NAME FROM UM_HYBRID_USER_ROLE, UM_DOMAIN WHERE "
                    + "UM_ROLE_ID=(SELECT TOP 1 UM_ID FROM UM_HYBRID_ROLE WHERE UM_ROLE_NAME=:UM_ROLE_NAME; AND "
                    + "UM_TENANT_ID=:UM_TENANT_ID;) AND UM_HYBRID_USER_ROLE.UM_TENANT_ID=:UM_TENANT_ID; "
                    + "AND UM_HYBRID_USER_ROLE.UM_DOMAIN_ID=UM_DOMAIN.UM_DOMAIN_ID";

    public static final String GET_GROUP_LIST_OF_ROLE_SQL =
            "SELECT UM_GROUP_NAME, UM_DOMAIN_NAME FROM UM_HYBRID_GROUP_ROLE, UM_DOMAIN WHERE "
                    + "UM_ROLE_ID=(SELECT UM_ID FROM UM_HYBRID_ROLE WHERE UM_ROLE_NAME=:UM_ROLE_NAME; AND "
                    + "UM_TENANT_ID=:UM_TENANT_ID; LIMIT 1) AND UM_HYBRID_GROUP_ROLE.UM_TENANT_ID=:UM_TENANT_ID; "
                    + "AND UM_HYBRID_GROUP_ROLE.UM_DOMAIN_ID=UM_DOMAIN.UM_DOMAIN_ID";

    public static final String GET_GROUP_LIST_OF_ROLE_SQL_MSSQL =
            "SELECT UM_GROUP_NAME, UM_DOMAIN_NAME FROM UM_HYBRID_GROUP_ROLE, UM_DOMAIN WHERE "
                    + "UM_ROLE_ID=(SELECT TOP 1 UM_ID FROM UM_HYBRID_ROLE WHERE UM_ROLE_NAME=:UM_ROLE_NAME; AND "
                    + "UM_TENANT_ID=:UM_TENANT_ID;) AND UM_HYBRID_GROUP_ROLE.UM_TENANT_ID=:UM_TENANT_ID; "
                    + "AND UM_HYBRID_GROUP_ROLE.UM_DOMAIN_ID=UM_DOMAIN.UM_DOMAIN_ID";

    // DB queries to list roles.
    public static final String GET_ROLES_BY_TENANT_MYSQL = "SELECT UM_ROLE_NAME FROM UM_HYBRID_ROLE WHERE "
            + "UM_TENANT_ID=:UM_TENANT_ID; ORDER BY UM_ID DESC LIMIT :OFFSET;, :LIMIT;";

    public static final String GET_ROLES_BY_TENANT_WITH_UUID_MYSQL = "SELECT UM_ROLE_NAME, UM_UUID FROM " +
            "UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID; ORDER BY UM_ID DESC LIMIT " +
            ":OFFSET;, :LIMIT;";

    public static final String GET_ROLES_BY_TENANT_ORACLE = "SELECT UM_ROLE_NAME FROM (SELECT UM_ROLE_NAME, rownum AS "
            + "rnum FROM (SELECT UM_ROLE_NAME FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID; " +
            "ORDER BY UM_ID DESC) WHERE rownum <= :END_INDEX;) WHERE rnum > :ZERO_BASED_START_INDEX;";

    public static final String GET_ROLES_BY_TENANT_WITH_UUID_ORACLE = "SELECT UM_ROLE_NAME, UM_UUID FROM (SELECT " +
            "UM_ROLE_NAME, rownum AS rnum FROM (SELECT UM_ROLE_NAME, UM_UUID FROM UM_HYBRID_ROLE WHERE " +
            "UM_TENANT_ID=:UM_TENANT_ID; ORDER BY UM_ID DESC) WHERE rownum <= :END_INDEX;) " +
            "WHERE rnum > :ZERO_BASED_START_INDEX;";

    public static final String GET_ROLES_BY_TENANT_MSSQL = "SELECT UM_ROLE_NAME FROM UM_HYBRID_ROLE WHERE "
            + "UM_TENANT_ID=:UM_TENANT_ID; ORDER BY UM_ID DESC OFFSET :OFFSET; ROWS FETCH NEXT :LIMIT; ROWS ONLY";

    public static final String GET_ROLES_BY_TENANT_WITH_UUID_MSSQL = "SELECT UM_ROLE_NAME, UM_UUID FROM " +
            "UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID; ORDER BY UM_ID DESC OFFSET " +
            ":OFFSET; ROWS FETCH NEXT :LIMIT; ROWS ONLY";

    public static final String GET_ROLES_BY_TENANT_POSTGRESQL = "SELECT UM_ROLE_NAME FROM UM_HYBRID_ROLE WHERE "
            + "UM_TENANT_ID=:UM_TENANT_ID; ORDER BY UM_ID DESC LIMIT :LIMIT; OFFSET :OFFSET;";

    public static final String GET_ROLES_BY_TENANT_WITH_UUID_POSTGRESQL = "SELECT UM_ROLE_NAME, UM_UUID FROM " +
            "UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID; ORDER BY UM_ID DESC " +
            "LIMIT :LIMIT; OFFSET :OFFSET;";

    public static final String GET_ROLES_BY_TENANT_DB2 = "SELECT UM_ROLE_NAME FROM (SELECT ROW_NUMBER() OVER(ORDER"
            + " BY UM_ID DESC) AS rn,UM_HYBRID_ROLE.* FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID;)WHERE rn "
            + "BETWEEN :ONE_BASED_START_INDEX; AND :END_INDEX;";

    public static final String GET_ROLES_BY_TENANT_WITH_UUID_DB2 = "SELECT UM_ROLE_NAME, UM_UUID FROM (SELECT " +
            "ROW_NUMBER() OVER(ORDER BY UM_ID DESC) AS rn,UM_HYBRID_ROLE.* FROM UM_HYBRID_ROLE WHERE " +
            "UM_TENANT_ID=:UM_TENANT_ID;)WHERE rn BETWEEN :ONE_BASED_START_INDEX; AND " +
            ":END_INDEX;";

    public static final String GET_ROLES_BY_TENANT_INFORMIX = "SELECT SKIP :OFFSET; FIRST "
            + ":LIMIT; UM_ROLE_NAME FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID; ORDER BY UM_ID DESC";

    public static final String GET_ROLES_BY_TENANT_WITH_UUID_INFORMIX = "SELECT SKIP :OFFSET; FIRST "
            + ":LIMIT; UM_ROLE_NAME, UM_UUID FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID; " +
            "ORDER BY UM_ID DESC";

    public static final String COUNT_ROLES_BY_TENANT_MYSQL = "SELECT COUNT(UM_ROLE_NAME) FROM UM_HYBRID_ROLE WHERE "
            + "UM_TENANT_ID=:UM_TENANT_ID;";

    public static final String COUNT_ROLES_BY_TENANT_ORACLE = "SELECT COUNT(UM_ROLE_NAME) FROM (SELECT UM_ROLE_NAME, "
            + "rownum AS rnum FROM (SELECT UM_ROLE_NAME FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID;))";

    public static final String COUNT_ROLES_BY_TENANT_MSSQL = "SELECT COUNT(UM_ROLE_NAME) FROM UM_HYBRID_ROLE WHERE "
            + "UM_TENANT_ID=:UM_TENANT_ID;";

    public static final String COUNT_ROLES_BY_TENANT_POSTGRESQL = "SELECT COUNT(UM_ROLE_NAME) FROM UM_HYBRID_ROLE "
            + "WHERE UM_TENANT_ID=:UM_TENANT_ID;";

    public static final String COUNT_ROLES_BY_TENANT_DB2 = "SELECT COUNT(UM_ROLE_NAME) FROM (SELECT ROW_NUMBER() "
            + "OVER(ORDER BY UM_ID DESC) AS rn,UM_HYBRID_ROLE.* FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID;)";

    public static final String COUNT_ROLES_BY_TENANT_INFORMIX = "SELECT COUNT(UM_ROLE_NAME) FROM UM_HYBRID_ROLE WHERE"
            + " UM_TENANT_ID=:UM_TENANT_ID;";

    // DB queries to filter roles.
    public static final String GET_ROLES_BY_TENANT_AND_ROLE_NAME_MYSQL = "SELECT UM_ROLE_NAME FROM UM_HYBRID_ROLE "
            + "WHERE UM_TENANT_ID=:UM_TENANT_ID; AND UM_ROLE_NAME LIKE :UM_ROLE_NAME; ORDER BY UM_ID DESC LIMIT "
            + ":OFFSET;, :LIMIT;";

    public static final String GET_ROLES_BY_TENANT_AND_ROLE_NAME_WITH_UUID_MYSQL = "SELECT UM_ROLE_NAME, UM_UUID " +
            "FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID; AND UM_ROLE_NAME LIKE :UM_ROLE_NAME; ORDER BY " +
            "UM_ID DESC LIMIT :OFFSET;, :LIMIT;";

    public static final String GET_ROLES_BY_TENANT_AND_ROLE_NAME_ORACLE =
            "SELECT UM_ROLE_NAME FROM (SELECT UM_ROLE_NAME, rownum AS "
                    + "rnum FROM (SELECT UM_ROLE_NAME FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID;"
                    + " ORDER BY UM_ID DESC) WHERE UM_ROLE_NAME LIKE :UM_ROLE_NAME; AND rownum <= :END_INDEX;) "
                    + "WHERE rnum > :ZERO_BASED_START_INDEX;";

    public static final String GET_ROLES_BY_TENANT_AND_ROLE_NAME_WITH_UUID_ORACLE =
            "SELECT UM_ROLE_NAME, UM_UUID FROM (SELECT UM_ROLE_NAME, UM_UUID, rownum AS rnum FROM (SELECT " +
                    "UM_ROLE_NAME, UM_UUID FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID; ORDER BY UM_ID " +
                    "DESC) WHERE UM_ROLE_NAME LIKE :UM_ROLE_NAME; AND rownum <= :END_INDEX;) WHERE rnum > " +
                    ":ZERO_BASED_START_INDEX;";

    public static final String GET_ROLES_BY_TENANT_AND_ROLE_NAME_MSSQL =
            "SELECT UM_ROLE_NAME FROM UM_HYBRID_ROLE WHERE "
                    + "UM_TENANT_ID=:UM_TENANT_ID; AND UM_ROLE_NAME LIKE :UM_ROLE_NAME; ORDER BY UM_ID DESC OFFSET "
                    + ":OFFSET; ROWS FETCH NEXT :LIMIT; ROWS ONLY";

    public static final String GET_ROLES_BY_TENANT_AND_ROLE_NAME_WITH_UUID_MSSQL =
            "SELECT UM_ROLE_NAME, UM_UUID FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID; AND " +
                    "UM_ROLE_NAME LIKE :UM_ROLE_NAME; ORDER BY UM_ID DESC OFFSET :OFFSET; ROWS FETCH NEXT :LIMIT; " +
                    "ROWS ONLY";

    public static final String GET_ROLES_BY_TENANT_AND_ROLE_NAME_POSTGRESQL =
            "SELECT UM_ROLE_NAME FROM UM_HYBRID_ROLE WHERE "
                    + "UM_TENANT_ID=:UM_TENANT_ID; AND UM_ROLE_NAME LIKE :UM_ROLE_NAME; ORDER BY UM_ID DESC LIMIT "
                    + ":LIMIT; OFFSET :OFFSET;";

    public static final String GET_ROLES_BY_TENANT_AND_ROLE_NAME_WITH_UUID_POSTGRESQL = "SELECT UM_ROLE_NAME, " +
            "UM_UUID FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID; AND UM_ROLE_NAME LIKE :UM_ROLE_NAME; " +
            "ORDER BY UM_ID DESC LIMIT :LIMIT; OFFSET :OFFSET;";

    public static final String GET_ROLES_BY_TENANT_AND_ROLE_NAME_DB2 =
            "SELECT UM_ROLE_NAME FROM (SELECT ROW_NUMBER() OVER(ORDER"
                    + " BY UM_ID DESC) AS rn,UM_HYBRID_ROLE.* FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID; "
                    + "AND UM_ROLE_NAME LIKE :UM_ROLE_NAME;)WHERE rn BETWEEN :ONE_BASED_START_INDEX; AND :END_INDEX;";

    public static final String GET_ROLES_BY_TENANT_AND_ROLE_NAME_WITH_UUID_DB2 =
            "SELECT UM_ROLE_NAME, UM_UUID FROM (SELECT ROW_NUMBER() OVER(ORDER"
                    + " BY UM_ID DESC) AS rn,UM_HYBRID_ROLE.* FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID; " +
                    "AND UM_ROLE_NAME LIKE :UM_ROLE_NAME;)WHERE rn BETWEEN :ONE_BASED_START_INDEX; AND :END_INDEX;";

    public static final String GET_ROLES_BY_TENANT_AND_ROLE_NAME_INFORMIX = "SELECT SKIP :OFFSET; FIRST "
            + ":LIMIT; UM_ROLE_NAME FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID; AND UM_ROLE_NAME LIKE "
            + ":UM_ROLE_NAME; ORDER BY UM_ID DESC";

    public static final String GET_ROLES_BY_TENANT_AND_ROLE_NAME_WITH_UUID_INFORMIX = "SELECT SKIP :OFFSET; FIRST "
            + ":LIMIT; UM_ROLE_NAME, UM_UUID FROM UM_HYBRID_ROLE WHERE UM_TENANT_ID=:UM_TENANT_ID; AND " +
            "UM_ROLE_NAME LIKE :UM_ROLE_NAME; ORDER BY UM_ID DESC";

    public static final String ADD_SCIM_ROLE_ID_SQL =
            "INSERT INTO IDN_SCIM_GROUP (TENANT_ID, ROLE_NAME, ATTR_NAME, ATTR_VALUE) VALUES (:TENANT_ID;, "
                    + ":ROLE_NAME;, :ATTR_NAME;, :ATTR_VALUE;)";

    public static final String DELETE_SCIM_ROLE_SQL = "DELETE FROM IDN_SCIM_GROUP WHERE TENANT_ID=:TENANT_ID; AND "
            + "ROLE_NAME=:ROLE_NAME;";

    public static final String UPDATE_SCIM_ROLE_NAME_SQL =
            "UPDATE IDN_SCIM_GROUP SET ROLE_NAME=:NEW_ROLE_NAME; WHERE TENANT_ID=:TENANT_ID; AND ROLE_NAME=:ROLE_NAME;";

    public static final String GET_ROLE_NAME_BY_ID_SQL = "SELECT ROLE_NAME FROM IDN_SCIM_GROUP WHERE "
            + "TENANT_ID=:TENANT_ID; AND ATTR_NAME=:ATTR_NAME; AND ATTR_VALUE=:ATTR_VALUE;";

    public static final String GET_ROLE_ID_BY_NAME_SQL = "SELECT ATTR_VALUE FROM IDN_SCIM_GROUP WHERE "
            + "TENANT_ID=:TENANT_ID; AND ROLE_NAME=:ROLE_NAME; AND ATTR_NAME=:ATTR_NAME;";

    public static final String IS_ROLE_ID_EXIST_SQL = "SELECT COUNT(ID) FROM IDN_SCIM_GROUP WHERE "
            + "TENANT_ID=:TENANT_ID; AND ATTR_NAME=:ATTR_NAME; AND ATTR_VALUE=:ATTR_VALUE;";

    // Groups related queries.
    public static final String GET_GROUP_NAME_BY_ID_SQL = "SELECT ROLE_NAME FROM IDN_SCIM_GROUP WHERE "
            + "TENANT_ID=:TENANT_ID; AND ATTR_NAME=:ATTR_NAME; AND ATTR_VALUE=:ATTR_VALUE;";
    public static final String GET_GROUP_ID_BY_NAME_SQL = "SELECT ATTR_VALUE FROM IDN_SCIM_GROUP WHERE "
            + "TENANT_ID=:TENANT_ID; AND ROLE_NAME=:ROLE_NAME; AND ATTR_NAME=:ATTR_NAME;";
}

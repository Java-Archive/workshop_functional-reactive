package org.rapidpm.workshop.frp.m01_lambdas.v001;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Copyright (C) 2010 RapidPM
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by RapidPM - Team on 06.12.16.
 */
public abstract class UserDAO {


  public void writeUser(final User user){
    final int update = update(user, connectionPool());
    // check result
  }

  private int update(User user, final JDBCConnectionPool connectionPool) {
    final DataSource dataSource = connectionPool.getDataSource();
    try {
      final Connection connection = dataSource.getConnection();
      final int count;
      try (final Statement statement = connection.createStatement()) {
        final String sql = createUpdteSQL(user);
        count = statement.executeUpdate(sql);
        statement.close();
      }
      dataSource.evictConnection(connection);
      return count;
    } catch (final SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  private String createUpdteSQL(final User user) {
    return ""; // vendor SQL , MySQL/Oracle...
  }


  public static class User { }

  public abstract JDBCConnectionPool connectionPool();

  public interface JDBCConnectionPool {
    DataSource getDataSource();
  }

  public interface DataSource {
    Connection getConnection() ;

    void evictConnection(Connection connection);
  }
}

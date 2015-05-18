databaseChangeLog {
  changeSet(id: '1431909191915-1', author: 'leonardo (generated)') {
    createTable(tableName: 'roles') {
      column(name: 'id', type: 'BIGSERIAL', autoIncrement: true) {
        constraints(primaryKey: true, primaryKeyName: 'roles_pkey')
      }
      column(name: 'name', type: 'VARCHAR(255)') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: '1431909191915-2', author: 'leonardo (generated)') {
    createTable(tableName: 'room_users') {
      column(name: 'rooms_code', type: 'VARCHAR(255)') {
        constraints(nullable: false)
      }
      column(name: 'users_id', type: 'INT8') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: '1431909191915-3', author: 'leonardo (generated)') {
    createTable(tableName: 'rooms') {
      column(name: 'code', type: 'VARCHAR(255)') {
        constraints(nullable: false)
      }
      column(name: 'created_at', type: 'TIMESTAMP WITH TIME ZONE') {
        constraints(nullable: false)
      }
      column(name: 'name', type: 'VARCHAR(255)') {
        constraints(nullable: false)
      }
      column(name: 'owner', type: 'INT8')
    }
  }

  changeSet(id: '1431909191915-4', author: 'leonardo (generated)') {
    createTable(tableName: 'users') {
      column(name: 'id', type: 'BIGSERIAL', autoIncrement: true) {
        constraints(primaryKey: true, primaryKeyName: 'users_pkey')
      }
      column(name: 'email', type: 'VARCHAR(255)') {
        constraints(nullable: false)
      }
      column(name: 'locked', type: 'BOOL')
      column(name: 'name', type: 'VARCHAR(255)') {
        constraints(nullable: false)
      }
      column(name: 'password', type: 'VARCHAR(255)') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: '1431909191915-5', author: 'leonardo (generated)') {
    createTable(tableName: 'users_roles') {
      column(name: 'users_id', type: 'INT8') {
        constraints(nullable: false)
      }
      column(name: 'roles_id', type: 'INT8') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: '1431909191915-6', author: 'leonardo (generated)') {
    addPrimaryKey(columnNames: 'code', constraintName: 'rooms_pkey', tableName: 'rooms')
  }

  changeSet(id: '1431909191915-7', author: 'leonardo (generated)') {
    addPrimaryKey(columnNames: 'users_id, roles_id', constraintName: 'users_roles_pkey', tableName: 'users_roles')
  }

  changeSet(id: '1431909191915-8', author: 'leonardo (generated)') {
    addForeignKeyConstraint(baseColumnNames: 'users_id', baseTableName: 'users_roles', constraintName: 'fk_3b2cl2u4ck187o21r4uhp6psv', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id', referencedTableName: 'users')
  }

  changeSet(id: '1431909191915-9', author: 'leonardo (generated)') {
    addForeignKeyConstraint(baseColumnNames: 'roles_id', baseTableName: 'users_roles', constraintName: 'fk_60loxav507l5mreo05v0im1lq', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id', referencedTableName: 'roles')
  }

  changeSet(id: '1431909191915-10', author: 'leonardo (generated)') {
    addForeignKeyConstraint(baseColumnNames: 'users_id', baseTableName: 'room_users', constraintName: 'fk_fgrdfm2ynovydls5hpdgo1p62', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id', referencedTableName: 'users')
  }

  changeSet(id: '1431909191915-11', author: 'leonardo (generated)') {
    addForeignKeyConstraint(baseColumnNames: 'owner', baseTableName: 'rooms', constraintName: 'fk_j3jqifyyb4qtyj5q6fkitspdr', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id', referencedTableName: 'users')
  }

  changeSet(id: '1431909191915-12', author: 'leonardo (generated)') {
    addForeignKeyConstraint(baseColumnNames: 'rooms_code', baseTableName: 'room_users', constraintName: 'fk_lfofc82hdmf03gnvvpbx6fhn4', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'code', referencedTableName: 'rooms')
  }

  changeSet(id: '1431909191915-13', author: 'leonardo (generated)') {
    addUniqueConstraint(columnNames: 'email', constraintName: 'uk_6dotkott2kjsp8vw4d0m25fb7', deferrable: false, disabled: false, initiallyDeferred: false, tableName: 'users')
  }

  includeAll(path:'migration/v1.0.0')
  
}

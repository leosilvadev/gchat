databaseChangeLog {
	changeSet(id: '1431228994598-1', author: 'leonardo (generated)') {
		createTable(tableName: 'roles') {
			column(name: 'id', type: 'BIGINT(19)', autoIncrement: true) { constraints(primaryKey: true) }
			column(name: 'name', type: 'VARCHAR(255)') { constraints(nullable: false) }
		}
	}

	changeSet(id: '1431228994598-2', author: 'leonardo (generated)') {
		createTable(tableName: 'room_users') {
			column(name: 'rooms_code', type: 'VARCHAR(255)') { constraints(nullable: false) }
			column(name: 'users_id', type: 'BIGINT(19)') { constraints(nullable: false) }
		}
	}

	changeSet(id: '1431228994598-3', author: 'leonardo (generated)') {
		createTable(tableName: 'rooms') {
			column(name: 'code', type: 'VARCHAR(255)') { constraints(nullable: false) }
			column(name: 'created_at', type: 'datetime(6)') { constraints(nullable: false) }
			column(name: 'name', type: 'VARCHAR(255)') { constraints(nullable: false) }
			column(name: 'owner', type: 'BIGINT(19)')
		}
	}

	changeSet(id: '1431228994598-4', author: 'leonardo (generated)') {
		createTable(tableName: 'users') {
			column(name: 'id', type: 'BIGINT(19)', autoIncrement: true) { constraints(primaryKey: true) }
			column(name: 'password', type: 'VARCHAR(255)') { constraints(nullable: false) }
			column(name: 'email', type: 'VARCHAR(255)') { constraints(nullable: false) }
			column(name: 'name', type: 'VARCHAR(255)') { constraints(nullable: false) }
			column(name: 'locked', type: 'BIT(1)')
		}
	}

	changeSet(id: '1431228994598-5', author: 'leonardo (generated)') {
		createTable(tableName: 'users_roles') {
			column(name: 'users_id', type: 'BIGINT(19)') { constraints(nullable: false) }
			column(name: 'roles_id', type: 'BIGINT(19)') { constraints(nullable: false) }
		}
	}

	changeSet(id: '1431228994598-6', author: 'leonardo (generated)') {
		addPrimaryKey(columnNames: 'code', constraintName: 'PRIMARY', tableName: 'rooms')
	}

	changeSet(id: '1431228994598-7', author: 'leonardo (generated)') {
		addPrimaryKey(columnNames: 'users_id, roles_id', constraintName: 'PRIMARY', tableName: 'users_roles')
	}

	changeSet(id: '1431228994598-8', author: 'leonardo (generated)') {
		addForeignKeyConstraint(baseColumnNames: 'users_id', baseTableName: 'users_roles', constraintName: 'FK_3b2cl2u4ck187o21r4uhp6psv', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id', referencedTableName: 'users')
	}

	changeSet(id: '1431228994598-9', author: 'leonardo (generated)') {
		addForeignKeyConstraint(baseColumnNames: 'roles_id', baseTableName: 'users_roles', constraintName: 'FK_60loxav507l5mreo05v0im1lq', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id', referencedTableName: 'roles')
	}

	changeSet(id: '1431228994598-10', author: 'leonardo (generated)') {
		addForeignKeyConstraint(baseColumnNames: 'users_id', baseTableName: 'room_users', constraintName: 'FK_fgrdfm2ynovydls5hpdgo1p62', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id', referencedTableName: 'users')
	}

	changeSet(id: '1431228994598-11', author: 'leonardo (generated)') {
		addForeignKeyConstraint(baseColumnNames: 'owner', baseTableName: 'rooms', constraintName: 'FK_j3jqifyyb4qtyj5q6fkitspdr', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id', referencedTableName: 'users')
	}

	changeSet(id: '1431228994598-12', author: 'leonardo (generated)') {
		addForeignKeyConstraint(baseColumnNames: 'rooms_code', baseTableName: 'room_users', constraintName: 'FK_lfofc82hdmf03gnvvpbx6fhn4', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'code', referencedTableName: 'rooms')
	}
	
	includeAll(path: 'migration/v1.0.0/')
}

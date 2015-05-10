databaseChangeLog {
	changeSet(author:"leonardo", id:"1431228994598-13"){
		insert(tableName:"roles"){
			column(name: "id", value: "1")
			column(name: "name", value: "ROLE_USER")
		}
		insert(tableName:"roles"){
			column(name: "id", value: "2")
			column(name: "name", value: "ROLE_ADMIN")
		}
	}
}

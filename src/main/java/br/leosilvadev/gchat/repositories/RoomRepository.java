package br.leosilvadev.gchat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.leosilvadev.gchat.model.domain.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, String> {

}

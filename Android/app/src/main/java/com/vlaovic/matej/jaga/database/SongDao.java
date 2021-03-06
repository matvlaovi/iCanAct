package com.vlaovic.matej.jaga.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SongDao {

    @Query("SELECT * FROM song")
    List<Song> getAll();

    @Query("SELECT * FROM song WHERE saved = 1")
    List<Song> getAllSaved();

    @Query("SELECT * FROM song WHERE title LIKE :search OR artist LIKE :search")
    List<Song> searchAll(String search);

    @Query("SELECT * FROM song WHERE (title LIKE :search OR artist LIKE :search) AND Saved = 1")
    List<Song> searchAllSaved(String search);

    @Query("SELECT * FROM song WHERE id = :id")
    Song getSongById(Integer id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNewSongs(List<Song> songs);

    @Query("DELETE FROM song")
    void deleteAllSongs();

    @Query("UPDATE song SET saved = :saved WHERE id = :id")
    void updateSaved(Integer saved, Integer id);
}

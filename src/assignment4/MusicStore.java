package assignment4;

import java.util.ArrayList;
import java.util.LinkedList;

public class MusicStore {
    //ADD YOUR CODE BELOW HERE
	MyHashTable<String,ArrayList<Song>> titleTable;
	MyHashTable<Integer,ArrayList<Song>> yearTable;
	MyHashTable<String,ArrayList<Song>> artistTable;
    //ADD YOUR CODE ABOVE HERE
    
    
    public MusicStore(ArrayList<Song> songs) {
        //ADD YOUR CODE BELOW HERE
    	
    	int numBuckets = 7;
    	
    	titleTable = new MyHashTable<String,ArrayList<Song>>(numBuckets);
    	yearTable = new MyHashTable<Integer,ArrayList<Song>>(numBuckets);
    	artistTable = new MyHashTable<String,ArrayList<Song>>(numBuckets);
    	
        for (Song song: songs) {
        	titleTable.put(song.getTitle(), new ArrayList<Song>());
        	yearTable.put(song.getYear(), new ArrayList<Song>());
        	artistTable.put(song.getArtist(), new ArrayList<Song>());
        }
        
        for(Song song: songs){
            this.addSong(song);
        }
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) {
        // ADD CODE BELOW HERE
    	titleTable.get(s.getTitle()).add(s);
    	yearTable.get(s.getYear()).add(s);
    	artistTable.get(s.getArtist()).add(s);	
        // ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) {
        //ADD CODE BELOW HERE
    	return titleTable.get(title).get(0); 
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) {
        //ADD CODE BELOW HERE 	
    	return artistTable.get(artist);
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) {
        //ADD CODE BELOW HERE   	
    	return yearTable.get(year);     
        //ADD CODE ABOVE HERE
        
    }
}

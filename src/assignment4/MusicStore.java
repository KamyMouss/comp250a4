package assignment4;

import java.util.ArrayList;
import java.util.LinkedList;

public class MusicStore {
    //ADD YOUR CODE BELOW HERE
	MyHashTable<String,Song> songTable;
    //ADD YOUR CODE ABOVE HERE
    
    
    public MusicStore(ArrayList<Song> songs) {
        //ADD YOUR CODE BELOW HERE
    	int numBuckets = 7;
    	songTable = new MyHashTable<String,Song>(numBuckets);
        for (Song song: songs) {
            songTable.put(song.getTitle(), song);
        }
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) {
        // ADD CODE BELOW HERE
        songTable.put(s.getTitle(), s);
        // ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) {
        //ADD CODE BELOW HERE
       return songTable.get(title);       
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) {
        //ADD CODE BELOW HERE
    	
    	ArrayList<Song> songs = new ArrayList<Song>();
    	
    	ArrayList<LinkedList<HashPair<String,Song>>> buckets = this.songTable.getBuckets();
    	
    	for (LinkedList<HashPair<String,Song>> bucket: buckets) 
    	{
    		for (HashPair<String,Song> pair: bucket) 
    		{
    			Song song = pair.getValue();
    			if (song.getArtist().equals(artist) || song.getTitle().contains(artist)) 
    			{
    				System.out.println(song.toString());
    				songs.add(song);
    			}
    		}	 
    	}    
    	
        return songs;
        
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) {
        //ADD CODE BELOW HERE
    	
    	ArrayList<Song> songs = new ArrayList<Song>();
    	
    	ArrayList<LinkedList<HashPair<String,Song>>> buckets = this.songTable.getBuckets();
    	
    	for (LinkedList<HashPair<String,Song>> bucket: buckets) 
    	{	
    		for (HashPair<String,Song> pair: bucket) 
    		{
    			Song song = pair.getValue();
    			if (song.getYear() == year) 
    			{
    				System.out.println(song.toString());
    				songs.add(song);		
    			}
    		} 
    	}    
    	
        return songs;
        
        //ADD CODE ABOVE HERE
        
    }
}

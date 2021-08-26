/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #1
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

public class Playlist{
    private final int MAX_SONGS = 50; //Max Songs
    //Creates an array of SongRecord object that holds all songs
    private SongRecord[] playlistSongs = new SongRecord[MAX_SONGS];
    private int playlistSize; //Size of Playlist. Will return later.

    /**
     * Default/Empty Constructor
     */
    public Playlist(){
        playlistSize = 0;
    }

    /**
     * @return
     *
     * Clone Method
     * Calls upon a helper method from SongRecord called clone
     */
    public Object clone(){
        Playlist clonePlaylistSongs = new Playlist();
        Playlist newList = new Playlist();
        int j = 1;
        for (int i = 0; i < playlistSize; i++){
            //Iterates through playlist and clones
            if (playlistSongs[i] != null) {
                clonePlaylistSongs.playlistSongs[i] =
                        (SongRecord) playlistSongs[i].clone();
            }
        }
        clonePlaylistSongs.playlistSize = playlistSize;
        return clonePlaylistSongs;
    }

    /**
     *
     * @param obj
     * Given object to check equality.
     *
     * @return
     * Checks to see if everything is equal.
     */
    public boolean equals(Object obj) {
        boolean bool1 = false;
        boolean bool2 = false;
        boolean bool3 = false;
        boolean bool4 = false;

        if (obj == null) {
            return false;
        }
        if (obj instanceof Playlist) {
            Playlist playlist = (Playlist) obj;
            for (int i = 0; i < playlistSize; i++) {
                if (playlist.playlistSongs[i].getTitle()
                        .equals(playlistSongs[i].getTitle())){
                    bool1 = true;
                }
                if (playlist.playlistSongs[i].getArtist()
                        .equals(playlistSongs[i].getArtist())){
                    bool2 = true;
                }
                if (playlist.playlistSongs[i].getSongLengthMinute()
                        == playlistSongs[i].getSongLengthMinute()){
                    bool3 = true;
                }
                if (playlist.playlistSongs[i].getSongLengthSecond()
                        == playlistSongs[i].getSongLengthSecond()){
                    bool4 = true;
                }
            }
        }
        return bool1 && bool2 && bool3 && bool4;
    }

    /**
     *
     * @return
     *
     * Returns the size of playlist
     */
    public int size(){
        return playlistSize;
    }

    /**
     *
     * @param song
     * given song to add
     *
     * @param position
     * given position to put the song into the playlist
     *
     * @throws IllegalArgumentException
     * Throws exception in case of invalid position or playlist being full.
     *
     * @throws FullPlayException
     *
     * Adds the song given position to playlist.
     */
    public void addSong(SongRecord song, int position)
            throws IllegalArgumentException, FullPlayException{
        if (position < 1 || position > playlistSize + 1){
            throw new IllegalArgumentException
                    ("Invalid position for adding the new song.");
        }
        if (playlistSize >= MAX_SONGS){
            throw new FullPlayException("Full playlist. Cannot add.");
        }
        position = position - 1;
        for (int i = playlistSize; i > position; i--) {
            playlistSongs[i] = playlistSongs[i - 1];
        }
        playlistSongs[position] = song;
        playlistSize++;
    }

    /**
     *
     * @param position
     * position of the song
     *
     * @throws IllegalArgumentException
     * Throws the exception when position is not valid.
     *
     * Removes the song given position from playlist.
     */
    public void removeSong(int position) throws IllegalArgumentException{
        if (position < 1 || position > playlistSize){
            throw new IllegalArgumentException
                    ("No song at position " + position + " to remove.");
        }
        int newSize = playlistSize - 1;

        for (int i = position - 1; i < newSize; i++){
            playlistSongs[i] = playlistSongs[i + 1];
        }
        playlistSize--;
    }

    /**
     *
     * @param position
     * Given position to get the song off of.
     *
     * @return
     * Returns the song given position.
     *
     * @throws IllegalArgumentException
     * Throws exception if position is not in valid range.
     *
     * Finds the song from given position and returns it.
     */
    public SongRecord getSong (int position) throws IllegalArgumentException{
        if (position < 1 || position > playlistSize){
            throw new IllegalArgumentException("Position not in valid range.");
        }
        int songPosition = position - 1;
        return playlistSongs[songPosition];
    }

    /**
     * Prints the table in I/O format.
     * Prints the playlist songs underneath the table in I/O format.
     */
    public void printAllSongs(){
        System.out.printf("%-7s%-5s%-5s%-12s%-6s%-12s%-6s%n",
                "Song #", "", "Title", "", "Artist", "", "Length");
        int i = 0;
        while (i <= 52) {
            System.out.print("-");
            i++;
        }
        System.out.println();
        for(int ii = 0; ii < playlistSize; ii++){

            String minuteLength =
                    String.valueOf(playlistSongs[ii].getSongLengthMinute());
            if (playlistSongs[ii].getSongLengthMinute() < 10){
                minuteLength = "0" + playlistSongs[ii].getSongLengthMinute();
            }

            String secondLength =
                    String.valueOf(playlistSongs[ii].getSongLengthSecond());
            if (playlistSongs[ii].getSongLengthSecond() < 10){
                secondLength = "0" + playlistSongs[ii].getSongLengthSecond();
            }

            String length = minuteLength + ":" + secondLength;

            System.out.printf("%-12d%-17s%-18s%-6s%n", ii + 1, playlistSongs[ii].getTitle(),
                    playlistSongs[ii].getArtist(), length);
        }
    }

    /**
     *
     * @param originalList
     * //Original Playlist that cannot be altered.
     *
     * @param artist
     * //The artist that is getting compared to check if same.
     *
     * @return
     *
     * Clone the original playlist, iterate through the entire playlist,
     * check to see if the song artist is equal to the artist at i,
     * if it is, then add that exact song information into a new playlist
     * and return the playlist.
     *
     * This method helps get all the songs by the specific artist.
     */
    public static Playlist getSongsByArtist(Playlist originalList, String artist){
        if (originalList == null || artist == null){
            return null;
        }
        Playlist songsByArtistPlaylist = (Playlist) originalList.clone();
        Playlist newList = new Playlist();
        int size = songsByArtistPlaylist.playlistSize;
        int j = 0;
        try {
            for (int i = 0; i < size; i++) {
                if (songsByArtistPlaylist.getSong(i + 1).getArtist().equals(artist)) {
                    ++j;
                    newList.addSong(songsByArtistPlaylist.getSong(i + 1), j);
                }
            }
        }
        catch (FullPlayException e){
            System.out.println(e.getMessage());
        }
        return newList;
    }

    /**
     *
     * @return
     *
     * toString method. Gets all the information into a string.
     */
    public String toString(){
        String str = "";
        for (int i = 0; i < playlistSize; i++){
            str += playlistSongs[i].toString();
        }
        return str;
    }

    /**
     *
     * @param playlist
     * //Given playlist to clone.
     *
     * @return
     *
     * Helper method that is used to check if clone is equal to playlist.
     */
    public static boolean checkClone (Playlist playlist){
        Playlist clonePlaylist = (Playlist) playlist.clone();
        return playlist.equals(clonePlaylist);
    }

    /**
     * Helper method
     *
     * @param position
     * Uses position to get the song's title and artist at that position
     *
     * @return
     * Returns the title and artist
     *
     * @throws IllegalArgumentException
     * Throws exception if position is not in valid range.
     *
     * Gets the title and artist and returns as a string.
     */
    public String getTitleAndArtist(int position) throws IllegalArgumentException{
        if (position < 1 || position > playlistSize){
            throw new IllegalArgumentException
                    ("No song at position " + position + " to remove.");
        }
        int songPosition = position - 1;
        return playlistSongs[songPosition].getTitle() +
                " by " + playlistSongs[songPosition].getArtist();
    }
}

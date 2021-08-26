/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #1
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

public class SongRecord{
    private String title;
    private String artist;
    private int songLengthMinute;
    private int songLengthSecond;

    /**
     *
     * @param title
     * @param artist
     * @param songLengthMinute
     * @param songLengthSecond
     *
     * Default Constructor
     */
    public SongRecord
    (String title, String artist, int songLengthMinute, int songLengthSecond){
        this.title = title;
        this.artist = artist;
        this.songLengthMinute = songLengthMinute;
        this.songLengthSecond = songLengthSecond;
    }

    /**
     *
     * @return
     *
     * Getter method for title
     */
    public String getTitle(){
        return title;
    }

    /**
     *
     * @return
     *
     * Getter method for artist
     */
    public String getArtist(){
        return artist;
    }

    /**
     *
     * @return
     *
     * Getter method for length of the song in minutes
     */
    public int getSongLengthMinute(){
        return songLengthMinute;
    }

    /**
     *
     * @return
     *
     * Getter method for the length of the song in seconds
     */
    public int getSongLengthSecond(){
        return songLengthSecond;
    }

    /**
     *
     * @param title
     *
     * Setter method for the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @param artist
     *
     * Setter method for artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     *
     * @param songLengthMinute
     * @throws IllegalArgumentException
     *
     * Setter method for the length of the song in minutes
     */
    public void setSongLengthMinute(int songLengthMinute)
            throws IllegalArgumentException {
        if (songLengthMinute < 0){
            throw new IllegalArgumentException
                    ("Minutes cannot be negative. Invalid song length.");
        }
        this.songLengthMinute = songLengthMinute;
    }

    /**
     *
     * @param songLengthSecond
     * @throws IllegalArgumentException
     *
     * Setter method for the length of the song in seconds
     */
    public void setSongLengthSecond(int songLengthSecond)
            throws IllegalArgumentException{
        if (songLengthSecond < 0){
            throw new IllegalArgumentException
                    ("Seconds cannot be negative. Invalid song length.");
        }
        if (songLengthSecond > 59){
            throw new IllegalArgumentException
                    ("Seconds cannot be greater than 59. Invalid song length.");
        }
        this.songLengthSecond = songLengthSecond;
    }

    /**
     *
     * @return
     *
     * toString method.
     * Prints the information about the audio file on a single line.
     */
    public String toString(){
        String newMinute = String.valueOf(this.songLengthMinute);
        if (this.songLengthMinute < 10){
            newMinute = "0" + this.songLengthMinute;
        }

        String newSecond = String.valueOf(this.songLengthSecond);
        if (this.songLengthSecond < 10){
            newSecond = "0" + this.songLengthSecond;
        }

        String newTime = newMinute + ":" + newSecond;

        return String.format
                ("%-11s%-17s%-18s%-6s", "", this.title, this.artist, newTime);
    }

    /**
     *
     * @return
     *
     * Helper method for clone in Playlist. Returns an identical SongRecord.
     */
    public Object clone(){
        SongRecord cloneSongRecord = new SongRecord
                (this.title, this.artist, this.songLengthMinute, this.songLengthSecond);
        return (Object) cloneSongRecord;
    }
}

/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #1
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

import java.util.InputMismatchException;
//Uses InputMismatchException for when user inputs i.e a string for an integer
import java.util.Scanner;
//Utilizes scanner to get user's inputs.

public class PlaylistOperations {
    public static void main (String[] args){ //Main class
        Scanner input = new Scanner(System.in); //Creates scanner
        boolean bool = true; //Continues loop until false
        SongRecord songs; //All the songs that is used.
        Playlist playlist = new Playlist(); //Creates a new playlist
        String title; //Title of song
        String artist; //Artist of song
        int minuteLength; //Length of song in minutes
        int secondLength; //Length of song in seconds
        int position; //Position of the song in playlist
        String userInput; //User's input
        String song; //A string that has the information of the songs
        int size; //Size of playlist

        System.out.println("Welcome to Playlist Operations!\n" +
                "Below are the available options.\n");

        while (bool) { //While loop. Iterates until false or 'Q'.
            //Put everything into a try catch case to catch all the exceptions in the end.
            try {
                System.out.println("A) Add Song \n" +
                        "B) Print Songs by Artist \n" +
                        "G) Get Song \n" +
                        "R) Remove Song \n" +
                        "P) Print All Songs \n" +
                        "S) Size \n" +
                        "Q) Quit ");
                System.out.print("Please input an option: ");
                userInput = input.nextLine().toUpperCase();
                System.out.println();

                switch (userInput) { //Uses switch case for user's inputs.
                    case "A": //Adds the songs
                        System.out.print("Enter the song title: ");
                        title = input.nextLine();
                        System.out.print("Enter the song artist: ");
                        artist = input.nextLine();
                        System.out.print("Enter the song length (minutes): ");
                        minuteLength = input.nextInt();
                        System.out.print("Enter the song length (seconds): ");
                        secondLength = input.nextInt();
                        System.out.print("Enter the position: ");
                        position = input.nextInt();

                        songs = new SongRecord(title, artist, minuteLength, secondLength);
                        //Used the setter afterwards just in case
                        //to catch any exception for length of song.
                        songs.setSongLengthMinute(minuteLength);
                        songs.setSongLengthSecond(secondLength);

                        playlist.addSong(songs, position);
                        //Adds song using method from Playlist

                        System.out.println("Song added: " + title + " by " + artist + "\n");
                        input.nextLine();
                        break;

                    case "B":
                        System.out.print("Enter the artist: ");
                        artist = input.nextLine();
                        System.out.println();
                        printTable(); //Helper method
                        int count = 1; //Displays song position
                        //Iterates through Playlist to get the song after getting them by artist
                        //Then prints it out onto a table.
                        for (int i = 0; i < Playlist.getSongsByArtist(playlist, artist).size(); i++) {
                            System.out.print(count);
                            System.out.println
                                    (Playlist.getSongsByArtist(playlist, artist).getSong(i + 1));
                            count++;
                        }
                        System.out.println();
                        break;

                    case "G": //Shows the song based off the position
                        System.out.print("Enter the position: ");
                        position = input.nextInt();
                        System.out.println();
                        printTable(); //Prints table
                        System.out.print(position);
                        song = String.valueOf(playlist.getSong(position));
                        //Puts the information of the song given position into a string
                        System.out.println(song);
                        System.out.println();
                        input.nextLine();
                        break;

                    case "R": //Removes song
                        System.out.print("Enter the position: ");
                        position = input.nextInt();
                        String str = playlist.getTitleAndArtist(position);
                        playlist.removeSong(position);
                        System.out.println("Song: " + str +
                                ", removed at position " + position + ".\n");
                        input.nextLine();
                        break;

                    case "P": //Prints all the songs
                        playlist.printAllSongs();
                        System.out.println();
                        break;

                    case "S": //Shows the size of the playlist.
                        size = playlist.size();
                        System.out.println("There are " + size + " " +
                                "song(s) in the current playlist.\n");
                        break;

                    case "Q": //Quits the program.
                        //Switches bool from true to false to terminate the loop and end the program.
                        bool = false;
                        System.out.println("Program terminating normally...");
                        break;

// This case checked to see if the clone is equal to playlist.
//                    case "C":
//                        System.out.println(Playlist.checkClone(playlist));
//                        break;

                    default: //Default case.
                        System.out.println("Please enter a viable option!\n");
                        break;
                }
            }
            //Catches these exceptions.
            //I use e.getMessage() for a personalized reason for exception
            //that is written in either Playlist or SongRecord.
            catch(FullPlayException | IllegalArgumentException e){
                System.out.println(e.getMessage()+"\n");
                input.nextLine();
            }
            //Catches InputMismatchException when there is a wrong format of input.
            //i.e: Entering a char or String when they ask for an integer.
            catch(InputMismatchException e){
                System.out.println("Please input the correct format of option. Try again.\n");
            }
        }
    }

    /**
     * Helper method that just helps create a table in main method.
     */
    public static void printTable(){
        System.out.printf("%-7s%-5s%-5s%-12s%-6s%-12s%-6s%n",
                "Song #", "", "Title", "", "Artist", "", "Length");
        int i = 0;
        while (i <= 52) {
            System.out.print("-");
            i++;
        }
        System.out.println();
    }
}

package Lab20_7;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * @author Alan Reeves
 */
public class Playlist {

    public static void main(String[] args) {
        String playlistTitle = "";
        Scanner scan = new Scanner(System.in);
        char userChoice;
        SongEntry headNode = new SongEntry();

        System.out.println("Enter playlist's title:");
        playlistTitle = scan.nextLine();
        System.out.println();

        userChoice = '0';

        while (userChoice != 'q') {
            printMenu(playlistTitle);
            userChoice = scan.nextLine().charAt(0);

            while (!inputChecker(userChoice)) {
                System.out.println("Choose an option:");
                userChoice = scan.nextLine().charAt(0);
            }
            headNode = executeMenu(userChoice, playlistTitle, headNode, scan);
            // scan.nextLine();
        }




        scan.close();
    }//end of main

    public static boolean inputChecker(char choice) {
        switch (choice) {
            case 'a':
            return true;
            case 'd':
            return true;
            case 'c':
            return true;
            case 's':
            return true;
            case 't':
            return true;
            case 'o':
            return true;
            case 'q':
            return true;
            case 'e':
            return true;
            default:
            return false;
        }
    }//end inputChecker

    public static void printMenu(String playlistTitle) {
        System.out.printf("%s PLAYLIST MENU\n", playlistTitle);
        System.out.println("a - Add song");
        System.out.println("d - Remove song");
        System.out.println("c - Change position of song");
        System.out.println("s - Output songs by specific artist");
        System.out.println("t - Output total time of playlist (in seconds)");
        System.out.println("o - Output full playlist");
        System.out.println("e - Switch two songs");
        System.out.println("q - Quit");
        System.out.println();
        System.out.println("Choose an option:");

    }//end of printMenu

    public static SongEntry executeMenu(char choice, String title, SongEntry headNode, Scanner scan) {
        switch (choice) {
            case 'q': {
                return headNode;
            }
            case 'o': {
                //working
                if (headNode == null) {
                    System.out.printf("%s - OUTPUT FULL PLAYLIST\nPlaylist is empty\n",
                    title);
                }

                int position = 1;
                SongEntry originalHead = headNode;
                System.out.printf("%s - OUTPUT FULL PLAYLIST\n", title);

                if (headNode.getNext() == null) {
                    System.out.println("Playlist is empty");
                    System.out.println();
                }

                while(headNode.getNext() != null) {
                    System.out.println(position + ".");
                    position++;
                    headNode.getNext().printPlaylistSongs();
                    System.out.println();
                    headNode = headNode.getNext();
                }
                return originalHead;
            }
            case 'a': {
                SongEntry originalHead = headNode;
                System.out.println("ADD SONG");
                System.out.println("Enter song's unique ID:");
                String newID = scan.nextLine();
                System.out.println("Enter song's name:");
                String newName = scan.nextLine();
                System.out.println("Enter artist's name:");
                String artistName = scan.nextLine();
                System.out.println("Enter song's length (in seconds):");
                int newLength = scan.nextInt();
                System.out.println();
                scan.nextLine();

                SongEntry newEntry = new SongEntry(newID, newName, artistName, newLength);

                //add in the RIGHT spot
                while (headNode.getNext() != null) {
                    headNode = headNode.getNext();
                }
                headNode.setNext(newEntry);


                return originalHead;
            }
            case 'd': {
                String deletedName = "Couldn't find song";
                SongEntry originalHead = headNode;
                System.out.println("REMOVE SONG");
                System.out.println("Enter song's unique ID:");
                String removeID = scan.nextLine();
                while (headNode.getNext() != null) {
                    if (headNode.getNext().getID().equals(removeID)) {
                        deletedName = headNode.getNext().getSongName();
                        if (headNode.getNext().getNext() != null) {
                            headNode.setNext(headNode.getNext().getNext());
                        } else {
                            headNode.setNext(null);
                            break;
                        }
                    }
                    headNode = headNode.getNext();
                }

                System.out.printf("\"%s\" removed.\n\n", deletedName);
                return originalHead;
            }
            case 'c': {
                SongEntry originalHead = headNode;
                System.out.println("CHANGE POSITION OF SONG");
                System.out.println("Enter song's current position:");
                int currentPosition = scan.nextInt();
                if (currentPosition < 1) {
                    currentPosition = 1;
                }
                System.out.println("Enter new position for song:");
                int newPosition = scan.nextInt();
                if (newPosition < 1) {
                    newPosition = 1;
                }
                
                
                //put nodes into an array
                ArrayList<SongEntry> arr = new ArrayList<>();
                arr.add(headNode);
                while (headNode.getNext() != null) {
                    arr.add(headNode.getNext());
                    headNode = headNode.getNext();
                }

                if (currentPosition >= arr.size() - 1) {
                    currentPosition = arr.size() - 1;
                }
                if (newPosition >= arr.size() - 1) {
                    newPosition = arr.size() - 1;
                }

                String songName = arr.get(currentPosition).getSongName();

                //move moveNode to newPosition
                //move all other nodes up
                SongEntry temp = arr.get(currentPosition);

                arr.remove(currentPosition);
                arr.add(newPosition, temp);


                //put linkedlist back together one at a time from the array
                //set nextNode as nodes come out of array
                for (int i = 0; i < arr.size() - 1; i++) {
                    arr.get(i).setNext(arr.get(i + 1));
                    }
                arr.get(arr.size() - 1).setNext(null);

                System.out.printf("\"%s\" moved to position %d\n", songName, newPosition);
                System.out.println();


                scan.nextLine();
                return originalHead;

            }
            case 't': {
                //output total time of playlist
                SongEntry originalHead = headNode;
                int totalTime = 0;
                while (headNode.getNext() != null) {
                    totalTime += headNode.getNext().getSongLength();
                    headNode = headNode.getNext();
                }
                System.out.println("OUTPUT TOTAL TIME OF PLAYLIST (IN SECONDS)");
                System.out.printf("Total time: %d seconds\n\n", totalTime);
                return originalHead;
            }
            case 's': {
                int position = 1;
                SongEntry originalHead = headNode;
                String searchName = "";

                System.out.println("OUTPUT SONGS BY SPECIFIC ARTIST");
                System.out.println("Enter artist's name:");
                searchName = scan.nextLine();

                while (headNode.getNext() != null) {
                    if (headNode.getNext().getArtistName().equals(searchName)) {
                        System.out.println(position + ".");
                        headNode.getNext().printPlaylistSongs();
                        System.out.println();
                    }
                    position += 1;
                    headNode = headNode.getNext();
                }
                return originalHead;
            }
            case 'e': {
                SongEntry originalHead = headNode;
                System.out.println("SWITCH TWO SONGS");
                System.out.println("Enter first song's position:");
                int song1Position = scan.nextInt();
                if (song1Position < 1) {
                    song1Position = 1;
                }
                System.out.println("Enter second song's position:");
                int song2Position = scan.nextInt();
                if (song2Position < 1) {
                    song2Position = 1;
                }

                //put nodes into array
                ArrayList<SongEntry> arr = new ArrayList<>();

                arr.add(headNode);
                while (headNode.getNext() != null) {
                    arr.add(headNode.getNext());
                    headNode = headNode.getNext();
                }

                if (song1Position >= arr.size() - 1) {
                    song1Position = arr.size() - 1;
                }
                if (song2Position >= arr.size() - 1) {
                    song2Position = arr.size() - 1;
                }

                String song1Name = arr.get(song1Position).getSongName();
                String song2Name = arr.get(song2Position).getSongName();

                //swap positions of indicated nodes
                SongEntry temp = arr.get(song2Position);
                arr.set(song2Position, arr.get(song1Position));
                arr.set(song1Position, temp);

                //put linked list back together as nodes come out of array
                for (int i = 0; i < arr.size() - 1; i++) {
                    arr.get(i).setNext(arr.get(i + 1));
                    }
                arr.get(arr.size() - 1).setNext(null);

                System.out.printf("\"%s\" swapped with %s\n", song1Name, song2Name);
                System.out.println();

                scan.nextLine();
                return originalHead;
            }
            default: {
                return headNode;
            }
        }
    }

}

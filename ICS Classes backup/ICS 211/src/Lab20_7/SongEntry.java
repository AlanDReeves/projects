package Lab20_7;

/*
 * @author Alan Reeves
 */
public class SongEntry {
private String uniqueID;
private String songName;
private String artistName;
private int songLength;
private SongEntry nextNode;

public SongEntry() {
    this.uniqueID = "none";
    this.songName = "none";
    this.artistName = "none";
    this.songLength = 0;
    this.nextNode = null;
}

public SongEntry(String uniqueID, String songName, String artistName, int songLength) {
    this.uniqueID = uniqueID;
    this.songName = songName;
    this.artistName = artistName;
    this.songLength = songLength;
    this.nextNode = null;
}

void insertAfter(SongEntry currNode) {
    currNode.setNext(this);
}

void setNext(SongEntry nextNode) {
    this.nextNode = nextNode;
}

String getID() {
    return uniqueID;
}

String getSongName() {
    return songName;
}

String getArtistName() {
    return artistName;
}

int getSongLength() {
    return songLength;
}

SongEntry getNext() {
    return nextNode;
}

void printPlaylistSongs() {
    System.out.printf("Unique ID: %s\n", uniqueID);
    System.out.printf("Song Name: %s\n", songName);
    System.out.printf("Artist Name: %s\n", artistName);
    System.out.printf("Song Length (in seconds): %d\n", songLength);
}


}

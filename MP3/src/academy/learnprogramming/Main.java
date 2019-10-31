package academy.learnprogramming;

import com.mpatric.mp3agic.*;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    static Mp3File createTag(Mp3File song, String song_name) throws IOException, NotSupportedException {
        String dir = "C:\\Users\\Kennedy\\Downloads\\" + song_name;
        song_name = (song_name.substring(0, song_name.lastIndexOf("."))); //Will remove the file extension
        ID3v2 id3v2Tag;
        id3v2Tag = new ID3v24Tag();
        song.setId3v2Tag(id3v2Tag);
        id3v2Tag.setTitle(song_name);
        if (id3v2Tag.getArtist() == null)
        {
            id3v2Tag.setArtist("N/A");
        }
        if (id3v2Tag.getYear() == null)
        {
            id3v2Tag.setYear("N/A");
        }
        if (id3v2Tag.getAlbum() == null)
        {
            id3v2Tag.setAlbum("N/A");
        }
        //System.out.println(dir);
        song.save("C:\\Users\\Kennedy\\Downloads\\test.mp3");
        //ID3v2 tag = song.getId3v2Tag();
        //System.out.println(tag.getTitle());
        return song;
    }

    public static void main(String[] args) throws InvalidDataException, IOException, UnsupportedTagException, NotSupportedException {

        File dir = new File("C:\\Users\\Kennedy\\Downloads");
        int i = 0;
        File[] files = dir.listFiles();
        List<File> mp3 = new ArrayList<File>();
        List<Songs> songs = new ArrayList<Songs>();
        //System.out.println("fast.mp3".indexOf("."));
        for (File file : files)
        {
            int index = file.getName().indexOf("."); // Holds the index of the dot
            //file.getName().substring(index);
            if (file.getName().substring(index).equals(".mp3"))
            {
                //System.out.println(file.getName());
                mp3.add(file);
                Mp3File song = new Mp3File(file);
                if (song.hasId3v2Tag())
                {
                    ID3v2 tag = song.getId3v2Tag();
                    //System.out.println(tag.getTitle());
                    if (tag.getTitle() == null)
                    {
                        song = createTag(song, file.getName());
                        tag = song.getId3v2Tag(); //Used to check title, artist, album, and year.
                        songs.add(new Songs(tag.getArtist(), tag.getYear(), tag.getAlbum(), tag.getTitle()));
                    }
                }
            }
            i++;
        }
        System.out.println(songs.get(0).getTitle());

    }
}


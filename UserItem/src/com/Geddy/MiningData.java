package com.Geddy;

import com.Geddy.Models.Item;
import com.Geddy.Models.UserPreference;

import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by lord_ on 16-2-2016.
 */
public class MiningData {

    public HashMap<String, UserPreference> readData(){
        HashMap<String, UserPreference> items = new HashMap<String,UserPreference>();

        try {
            final Scanner data = new Scanner(new FileReader("u.data"));
            while (data.hasNext()) {
                final String[] columns = data.nextLine().split("\\t");
                if (items.containsKey(columns[0])){

                    UserPreference user = items.get(columns[0]);
                    user.setRating(Integer.parseInt(columns[1]),Double.parseDouble(columns[2]));
                    items.put(columns[0], user);
                }else {
                    UserPreference userPreference = new UserPreference(Integer.parseInt(columns[0]), Integer.parseInt(columns[1]),Double.parseDouble(columns[2]));
                    items.put(columns[0], userPreference);
                }
            }
            for(Map.Entry<String, UserPreference> entry : items.entrySet()) {
                System.out.println(entry.getKey() + "  => " + entry.getValue().getRatings());
            }
        }catch(Exception e){
            System.out.println(e);
        }

        return items;
    }

    public HashMap<Integer,String> getGenreFromFile(){
        HashMap<Integer,String> genre = new HashMap<Integer, String>();

        try{
            final Scanner genreData = new Scanner(new FileReader("u.genre"));
            while (genreData.hasNext()){
                final String[] columns = genreData.next().split("[|]");
                genre.put(Integer.parseInt(columns[1]),columns[0]);
            }

        }catch(Exception e){
            System.out.println(e);
        }

        for(Map.Entry<Integer,String> entry : genre.entrySet()) {
            System.out.println(entry.getKey() + "  => " + entry.getValue());
        }
        return genre;

    }

    public HashMap<Integer,Item>getMovieFromFile(HashMap<Integer,String> genres){
        HashMap<Integer,Item> movies = new HashMap<Integer, Item>();

        try{
            final Scanner genreData = new Scanner(new FileReader("u.item"));
            while (genreData.hasNext()){
                final String[] columns = genreData.nextLine().split("\\|");
                int id = Integer.parseInt(columns[0]);
                String name = columns[1];
                String date = columns[2];
                String imdb = columns[4];
                ArrayList<String> genre = new ArrayList<String>();

                int count = 5;
                for(int i = 0; i < columns.length - 5; i++){
                    if( Integer.parseInt(columns[count]) == 1 ) {
                        genre.add(genres.get(i));
                    }
                    count++;
                }
                Item item = new Item(id,name,date,imdb,genre);
                movies.put(Integer.parseInt(columns[0]),item);
            }

        }catch(Exception e){
            System.out.println(e);
        }

        for(Map.Entry<Integer,Item> entry : movies.entrySet()) {
            System.out.println(entry.getKey() + "  => " + entry.getValue().getName() + " / " + entry.getValue().getGenre() );
        }
        return movies;
    }
}

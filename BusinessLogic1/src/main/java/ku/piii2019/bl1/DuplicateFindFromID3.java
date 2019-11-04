/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.bl1;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author James
 */
public class DuplicateFindFromID3 implements DuplicateFinder{

    @Override
    public boolean areDuplicates(MediaItem m1, MediaItem m2) {
       // throw new UnsupportedOperationException("Not written yet."); //To change body of generated methods, choose Tools | Templates.
    return !m1.getAbsolutePath().equals(m2.getAbsolutePath()) 
                && m1.getArtist().trim().toLowerCase().equals(m2.getArtist().trim().toLowerCase())
                && m1.getAlbum().trim().toLowerCase().equals(m2.getAlbum().trim().toLowerCase())
                && m1.getTitle().trim().toLowerCase().equals(m2.getTitle().trim().toLowerCase());
    }

    
}

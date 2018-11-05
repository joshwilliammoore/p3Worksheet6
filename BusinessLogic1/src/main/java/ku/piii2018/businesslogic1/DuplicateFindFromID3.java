/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2018.businesslogic1;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author James
 */
public class DuplicateFindFromID3 implements DuplicateFinder{


    @Override
    public boolean areDuplicates(MediaItem m1, MediaItem m2) {
        if(m1==m2)
            return true;
        
        if((m1.getAlbum()==null) || 
           !m1.getAlbum().equals(m2.getAlbum())) {
            return false;
        }
        if((m1.getArtist()==null) || 
           !m1.getArtist().equals(m2.getArtist())) {
            return false;          
        }
        if((m1.getTitle()==null) || 
           !m1.getTitle().equals(m2.getTitle())) {
            return false;            
        }
        return true;
    }
    
}

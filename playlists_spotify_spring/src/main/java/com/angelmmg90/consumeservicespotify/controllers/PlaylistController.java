package com.angelmmg90.consumeservicespotify.controllers;

import com.angelmmg90.consumeservicespotify.models.Playlist;
import static com.angelmmg90.consumerservicespotify.configuration.SpringWebConfig.getTemplate;
import java.io.IOException;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author amacdong
 */

@RestController 
public class PlaylistController {
    
    @RequestMapping("/playlists/{playlist_id}")
    public @ResponseBody Playlist showData(Model model,@PathVariable("playlist_id")String id) throws IOException{
        
                
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.spotify.com/v1/users/yourUserName/");
        final URI uri = builder.path("playlists/{playlist_id}").build().expand(id).encode().toUri();
        final ResponseEntity<Playlist> response = getTemplate().getForEntity(uri, Playlist.class);
 
        Playlist playlist;
        playlist = response.getBody();
        return playlist;
        
        
        // return response.getBody();
       /* ArrayList<Image> images;
        images = response.getBody().getImages();
        
        model.addAttribute("id", response.getBody().getId());
        model.addAttribute("name", response.getBody().getName());
        model.addAttribute("description", response.getBody().getDescription());
        model.addAttribute("href", response.getBody().getHref());
        model.addAttribute("collaborative", response.getBody().isCollaborative());
        
        for (int i = 0; i < images.size(); i++) {
            model.addAttribute("url", images.get(i).getUrl());
            model.addAttribute("height", images.get(i).getHeight());
            model.addAttribute("width", images.get(i).getWidth());
	}
        
        
        
        
        return "playlist";*/
       
       
    }
    
}

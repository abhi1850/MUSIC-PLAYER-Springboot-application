package com.example.musicplayer.controller;

import java.net.URI;
import java.util.List;

import com.example.musicplayer.helper.SongUploadHelper;
import com.example.musicplayer.model.Login;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.musicplayer.model.Song;
import com.example.musicplayer.service.SongService;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SongController {
	@Autowired
	private SongService Service;

	@GetMapping("/index")
	public String viewHomePage(Model model) {
		List<Song> listSong = Service.listAll();
		model.addAttribute("listSong", listSong);
		System.out.print("Get / ");
		return "index";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveSong(@ModelAttribute("Song") Song std) {
		Service.save(std);
		return "redirect:/index";
	}

	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("Song", new Song());
		return "new";
	}

	@Autowired
	private SongUploadHelper fileUploadHelper;

	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

		try {
			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
			}
			if (!file.getContentType().equals("audio/mpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only mp3 files");
			}
			//file upload code
			boolean f = fileUploadHelper.uploadFile(file);
			if (f) {
				return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong Try again!");
	}

	@RequestMapping("/delete/{id}")
	public String deleteSong(@PathVariable(name = "id") int id) {
		Service.delete(id);
		return "redirect:/";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditSongPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("new");
		Song std = Service.get(id);
		mav.addObject("Song", std);
		return mav;

	}

	@GetMapping("/search-song")
	public String search(@RequestParam("title") String title, Model model) {
		List<Song> songs = Service.findByTitle(title);
		model.addAttribute("listSong", songs);
		return "index";
	}

	@GetMapping("/search")
	public String ViewSearchPage() {
		return "search";
	}
}
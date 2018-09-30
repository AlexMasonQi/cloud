package com.alex.cloud.controller;

import com.alex.cloud.entity.AudioLibrary;
import com.alex.cloud.service.AudioLibraryQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ProviderAudioController
{
    @Autowired
    private AudioLibraryQueryService libraryQueryService;

    @GetMapping("/{id}")
    public AudioLibrary selectAudioById(@PathVariable Integer id)
    {
        log.info("test sleuth");
        return libraryQueryService.selectAudioById(id);
    }

    @GetMapping("/allAudio")
    public List<AudioLibrary> selectAllAudios()
    {
        return libraryQueryService.selectAllAudios();
    }
}

package com.alex.cloud.service;

import com.alex.cloud.dao.AudioLibraryDao;
import com.alex.cloud.entity.AudioLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudioLibraryQueryService
{
    @Autowired
    private AudioLibraryDao libraryDao;

    public AudioLibrary selectAudioById(Integer id)
    {
        return libraryDao.selectAudioById(id);
    }

    public List<AudioLibrary> selectAllAudios()
    {
        return libraryDao.selectAllAudio();
    }
}

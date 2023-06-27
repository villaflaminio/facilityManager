package it.flaminiovilla.facilitymanager.service.impl;

import it.flaminiovilla.facilitymanager.model.entity.File;
import it.flaminiovilla.facilitymanager.repository.FileRepository;
import it.flaminiovilla.facilitymanager.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    public File store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File FileDB = new File(null, fileName, file.getContentType(), file.getBytes());

        return fileRepository.save(FileDB);
    }

    public File getFile(Long id) {
        return fileRepository.findById(id).get();
    }

    public Stream<File> getAllFiles() {
        return fileRepository.findAll().stream();
    }
}

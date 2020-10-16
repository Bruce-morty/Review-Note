package com.cskaoyan.service;

import com.cskaoyan.bean.Storage;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
     int  insertStorage(Storage storage);

     Storage queryStorage(Storage storage);

    Storage storageFile(MultipartFile file);
}

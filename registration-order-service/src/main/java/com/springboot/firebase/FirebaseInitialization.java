package com.springboot.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;


@Service
public class FirebaseInitialization {

    @PostConstruct
    public void connectionInitialization() throws IOException {
        ClassLoader classLoader = FirebaseInitialization.class.getClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccount.json")).getFile());

        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }
}

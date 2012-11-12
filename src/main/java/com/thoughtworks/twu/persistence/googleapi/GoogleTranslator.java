package com.thoughtworks.twu.persistence.googleapi;

import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;
import com.thoughtworks.twu.domain.Book;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//understands how to turn lists of volumes into lists of books
@Component
public class GoogleTranslator {

    public List<Book> translate(Volumes volumes) {
        List<Book> books = new ArrayList<Book>();
        if(volumes.getItems() == null) return books;

        for (Volume volume : volumes.getItems()) {
            Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
            books.add(new Book(authors(volumeInfo), title(volumeInfo), imageLinks(volumeInfo),
                    volumeInfo.getDescription(), ISBN10(volumeInfo), ISBN13(volumeInfo), 0));
        }
        return books;
    }

    private String title(Volume.VolumeInfo volumeInfo) {
        return volumeInfo.getTitle();
    }

    private String authors(Volume.VolumeInfo volumeInfo) {
        if (volumeInfo.getAuthors() == null) return StringUtils.EMPTY;

        StringBuffer authors = new StringBuffer();
        for (String item : volumeInfo.getAuthors()) {
            authors.append(item).append(" ");
        }
        return authors.toString().trim();
    }

    private String imageLinks(Volume.VolumeInfo volumeInfo) {
        Volume.VolumeInfo.ImageLinks imageLinks = volumeInfo.getImageLinks();
        if (imageLinks != null) {
            return imageLinks.getThumbnail();
        }
        return null;
    }

    private String ISBN10(Volume.VolumeInfo volumeInfo) {
        List<Volume.VolumeInfo.IndustryIdentifiers> industryIdentifiers = volumeInfo.getIndustryIdentifiers();
        if (industryIdentifiers != null && industryIdentifiers.size() >= 1) {
            return volumeInfo.getIndustryIdentifiers().get(0).getIdentifier();
        }
        return "";
    }

    private String ISBN13(Volume.VolumeInfo volumeInfo) {
        List<Volume.VolumeInfo.IndustryIdentifiers> industryIdentifiers = volumeInfo.getIndustryIdentifiers();
        if (industryIdentifiers != null && industryIdentifiers.size() >= 2) {
            return volumeInfo.getIndustryIdentifiers().get(1).getIdentifier();
        }
        return "";
    }
}

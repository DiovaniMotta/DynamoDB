package br.com.senior.dynamodb.builder;

import java.util.Locale;

import org.json.JSONObject;

import br.com.senior.dynamodb.entity.Resume;
import br.com.senior.dynamodb.entity.ResumeKey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResumeBuilder {

    Resume resume;

    public static ResumeBuilder oneResume() {
        ResumeBuilder builder = new ResumeBuilder();
        builder.resume = new Resume();
        builder.resume.setInformation(new JSONObject());
        return builder;
    }

    public ResumeBuilder resume(String key, Object value) {
        this.resume.getInformation().put(key, value);
        return this;
    }

    public ResumeKeyBuilder createKey() {
        ResumeKeyBuilder builder = ResumeKeyBuilder.oneResumeKey(this);
        return builder;
    }

    public ResumeBuilder genericValue(String genericValue){
        this.resume.setGenericValue(genericValue);
        return this;
    }

    public Resume toResume() {
        return resume;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ResumeKeyBuilder {

        ResumeKey key;
        ResumeBuilder resumeBuilder;

        public static ResumeKeyBuilder oneResumeKey() {
            ResumeKeyBuilder builder = new ResumeKeyBuilder();
            builder.key = new ResumeKey();
            return builder;
        }


        public static ResumeKeyBuilder oneResumeKey(ResumeBuilder resumeBuilder) {
            ResumeKeyBuilder builder = new ResumeKeyBuilder();
            builder.key = new ResumeKey();
            builder.resumeBuilder = resumeBuilder;
            return builder;
        }

        public ResumeKeyBuilder document(String document) {
            this.key.setDocument(document);
            return this;
        }

        public ResumeKeyBuilder typeDocument(String type) {
            this.key.setTypeDocument(type);
            return this;
        }

        public ResumeKeyBuilder locale(Locale locale) {
            this.key.setLocale(locale);
            return this;
        }

        public ResumeKey toKey() {
            return key;
        }

        public ResumeBuilder addKey(){
            resumeBuilder.resume.setKey(toKey());
            return resumeBuilder;
        }
    }
}

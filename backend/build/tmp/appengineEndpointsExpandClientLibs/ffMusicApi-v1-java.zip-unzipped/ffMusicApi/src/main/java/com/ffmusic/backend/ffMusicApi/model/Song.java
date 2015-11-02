/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2015-08-03 17:34:38 UTC)
 * on 2015-11-02 at 15:31:02 UTC 
 * Modify at your own risk.
 */

package com.ffmusic.backend.ffMusicApi.model;

/**
 * Model definition for Song.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the ffMusicApi. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Song extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String artist;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String songName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String songYoutubeId;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String thumbnailURL;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getArtist() {
    return artist;
  }

  /**
   * @param artist artist or {@code null} for none
   */
  public Song setArtist(java.lang.String artist) {
    this.artist = artist;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public Song setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSongName() {
    return songName;
  }

  /**
   * @param songName songName or {@code null} for none
   */
  public Song setSongName(java.lang.String songName) {
    this.songName = songName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSongYoutubeId() {
    return songYoutubeId;
  }

  /**
   * @param songYoutubeId songYoutubeId or {@code null} for none
   */
  public Song setSongYoutubeId(java.lang.String songYoutubeId) {
    this.songYoutubeId = songYoutubeId;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getThumbnailURL() {
    return thumbnailURL;
  }

  /**
   * @param thumbnailURL thumbnailURL or {@code null} for none
   */
  public Song setThumbnailURL(java.lang.String thumbnailURL) {
    this.thumbnailURL = thumbnailURL;
    return this;
  }

  @Override
  public Song set(String fieldName, Object value) {
    return (Song) super.set(fieldName, value);
  }

  @Override
  public Song clone() {
    return (Song) super.clone();
  }

}

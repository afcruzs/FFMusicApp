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
 * on 2015-10-27 at 01:49:42 UTC 
 * Modify at your own risk.
 */

package com.ffmusic.backend.ffMusicApi.model;

/**
 * Model definition for SongRoom.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the ffMusicApi. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class SongRoom extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private User createdBy;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer idxInQueue;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Room room;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Song song;

  /**
   * @return value or {@code null} for none
   */
  public User getCreatedBy() {
    return createdBy;
  }

  /**
   * @param createdBy createdBy or {@code null} for none
   */
  public SongRoom setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
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
  public SongRoom setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getIdxInQueue() {
    return idxInQueue;
  }

  /**
   * @param idxInQueue idxInQueue or {@code null} for none
   */
  public SongRoom setIdxInQueue(java.lang.Integer idxInQueue) {
    this.idxInQueue = idxInQueue;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Room getRoom() {
    return room;
  }

  /**
   * @param room room or {@code null} for none
   */
  public SongRoom setRoom(Room room) {
    this.room = room;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Song getSong() {
    return song;
  }

  /**
   * @param song song or {@code null} for none
   */
  public SongRoom setSong(Song song) {
    this.song = song;
    return this;
  }

  @Override
  public SongRoom set(String fieldName, Object value) {
    return (SongRoom) super.set(fieldName, value);
  }

  @Override
  public SongRoom clone() {
    return (SongRoom) super.clone();
  }

}

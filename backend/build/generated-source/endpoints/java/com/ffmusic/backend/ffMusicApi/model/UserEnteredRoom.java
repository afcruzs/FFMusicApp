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
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2015-11-16 19:10:01 UTC)
 * on 2015-11-24 at 20:41:12 UTC 
 * Modify at your own risk.
 */

package com.ffmusic.backend.ffMusicApi.model;

/**
 * Model definition for UserEnteredRoom.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the ffMusicApi. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class UserEnteredRoom extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Room room;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private User user;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public UserEnteredRoom setId(java.lang.Long id) {
    this.id = id;
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
  public UserEnteredRoom setRoom(Room room) {
    this.room = room;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public User getUser() {
    return user;
  }

  /**
   * @param user user or {@code null} for none
   */
  public UserEnteredRoom setUser(User user) {
    this.user = user;
    return this;
  }

  @Override
  public UserEnteredRoom set(String fieldName, Object value) {
    return (UserEnteredRoom) super.set(fieldName, value);
  }

  @Override
  public UserEnteredRoom clone() {
    return (UserEnteredRoom) super.clone();
  }

}

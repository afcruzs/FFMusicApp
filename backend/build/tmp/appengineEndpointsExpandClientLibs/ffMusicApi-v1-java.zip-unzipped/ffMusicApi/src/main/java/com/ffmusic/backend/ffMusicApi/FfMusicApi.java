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
 * on 2015-10-31 at 04:36:20 UTC 
 * Modify at your own risk.
 */

package com.ffmusic.backend.ffMusicApi;

/**
 * Service definition for FfMusicApi (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link FfMusicApiRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class FfMusicApi extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.20.0 of the ffMusicApi library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://ffmusicbackend.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "ffMusicApi/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public FfMusicApi(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  FfMusicApi(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * An accessor for creating requests from the RoomEndPoint collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code FfMusicApi ffMusicApi = new FfMusicApi(...);}
   *   {@code FfMusicApi.RoomEndPoint.List request = ffMusicApi.roomEndPoint().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public RoomEndPoint roomEndPoint() {
    return new RoomEndPoint();
  }

  /**
   * The "roomEndPoint" collection of methods.
   */
  public class RoomEndPoint {

    /**
     * Create a request for the method "roomEndPoint.getRoomById".
     *
     * This request holds the parameters needed by the ffMusicApi server.  After setting any optional
     * parameters, call the {@link GetRoomById#execute()} method to invoke the remote operation.
     *
     * @param idRoom
     * @return the request
     */
    public GetRoomById getRoomById(java.lang.Long idRoom) throws java.io.IOException {
      GetRoomById result = new GetRoomById(idRoom);
      initialize(result);
      return result;
    }

    public class GetRoomById extends FfMusicApiRequest<com.ffmusic.backend.ffMusicApi.model.Room> {

      private static final String REST_PATH = "room/{idRoom}";

      /**
       * Create a request for the method "roomEndPoint.getRoomById".
       *
       * This request holds the parameters needed by the the ffMusicApi server.  After setting any
       * optional parameters, call the {@link GetRoomById#execute()} method to invoke the remote
       * operation. <p> {@link
       * GetRoomById#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param idRoom
       * @since 1.13
       */
      protected GetRoomById(java.lang.Long idRoom) {
        super(FfMusicApi.this, "GET", REST_PATH, null, com.ffmusic.backend.ffMusicApi.model.Room.class);
        this.idRoom = com.google.api.client.util.Preconditions.checkNotNull(idRoom, "Required parameter idRoom must be specified.");
      }

      @Override
      public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
        return super.executeUsingHead();
      }

      @Override
      public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
        return super.buildHttpRequestUsingHead();
      }

      @Override
      public GetRoomById setAlt(java.lang.String alt) {
        return (GetRoomById) super.setAlt(alt);
      }

      @Override
      public GetRoomById setFields(java.lang.String fields) {
        return (GetRoomById) super.setFields(fields);
      }

      @Override
      public GetRoomById setKey(java.lang.String key) {
        return (GetRoomById) super.setKey(key);
      }

      @Override
      public GetRoomById setOauthToken(java.lang.String oauthToken) {
        return (GetRoomById) super.setOauthToken(oauthToken);
      }

      @Override
      public GetRoomById setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (GetRoomById) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public GetRoomById setQuotaUser(java.lang.String quotaUser) {
        return (GetRoomById) super.setQuotaUser(quotaUser);
      }

      @Override
      public GetRoomById setUserIp(java.lang.String userIp) {
        return (GetRoomById) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private java.lang.Long idRoom;

      /**

       */
      public java.lang.Long getIdRoom() {
        return idRoom;
      }

      public GetRoomById setIdRoom(java.lang.Long idRoom) {
        this.idRoom = idRoom;
        return this;
      }

      @Override
      public GetRoomById set(String parameterName, Object value) {
        return (GetRoomById) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "roomEndPoint.insertRoom".
     *
     * This request holds the parameters needed by the ffMusicApi server.  After setting any optional
     * parameters, call the {@link InsertRoom#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.ffmusic.backend.ffMusicApi.model.Room}
     * @return the request
     */
    public InsertRoom insertRoom(com.ffmusic.backend.ffMusicApi.model.Room content) throws java.io.IOException {
      InsertRoom result = new InsertRoom(content);
      initialize(result);
      return result;
    }

    public class InsertRoom extends FfMusicApiRequest<com.ffmusic.backend.ffMusicApi.model.Room> {

      private static final String REST_PATH = "room";

      /**
       * Create a request for the method "roomEndPoint.insertRoom".
       *
       * This request holds the parameters needed by the the ffMusicApi server.  After setting any
       * optional parameters, call the {@link InsertRoom#execute()} method to invoke the remote
       * operation. <p> {@link
       * InsertRoom#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.ffmusic.backend.ffMusicApi.model.Room}
       * @since 1.13
       */
      protected InsertRoom(com.ffmusic.backend.ffMusicApi.model.Room content) {
        super(FfMusicApi.this, "POST", REST_PATH, content, com.ffmusic.backend.ffMusicApi.model.Room.class);
      }

      @Override
      public InsertRoom setAlt(java.lang.String alt) {
        return (InsertRoom) super.setAlt(alt);
      }

      @Override
      public InsertRoom setFields(java.lang.String fields) {
        return (InsertRoom) super.setFields(fields);
      }

      @Override
      public InsertRoom setKey(java.lang.String key) {
        return (InsertRoom) super.setKey(key);
      }

      @Override
      public InsertRoom setOauthToken(java.lang.String oauthToken) {
        return (InsertRoom) super.setOauthToken(oauthToken);
      }

      @Override
      public InsertRoom setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (InsertRoom) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public InsertRoom setQuotaUser(java.lang.String quotaUser) {
        return (InsertRoom) super.setQuotaUser(quotaUser);
      }

      @Override
      public InsertRoom setUserIp(java.lang.String userIp) {
        return (InsertRoom) super.setUserIp(userIp);
      }

      @Override
      public InsertRoom set(String parameterName, Object value) {
        return (InsertRoom) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "roomEndPoint.insertSong".
     *
     * This request holds the parameters needed by the ffMusicApi server.  After setting any optional
     * parameters, call the {@link InsertSong#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.ffmusic.backend.ffMusicApi.model.Song}
     * @return the request
     */
    public InsertSong insertSong(com.ffmusic.backend.ffMusicApi.model.Song content) throws java.io.IOException {
      InsertSong result = new InsertSong(content);
      initialize(result);
      return result;
    }

    public class InsertSong extends FfMusicApiRequest<com.ffmusic.backend.ffMusicApi.model.Song> {

      private static final String REST_PATH = "song";

      /**
       * Create a request for the method "roomEndPoint.insertSong".
       *
       * This request holds the parameters needed by the the ffMusicApi server.  After setting any
       * optional parameters, call the {@link InsertSong#execute()} method to invoke the remote
       * operation. <p> {@link
       * InsertSong#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.ffmusic.backend.ffMusicApi.model.Song}
       * @since 1.13
       */
      protected InsertSong(com.ffmusic.backend.ffMusicApi.model.Song content) {
        super(FfMusicApi.this, "POST", REST_PATH, content, com.ffmusic.backend.ffMusicApi.model.Song.class);
      }

      @Override
      public InsertSong setAlt(java.lang.String alt) {
        return (InsertSong) super.setAlt(alt);
      }

      @Override
      public InsertSong setFields(java.lang.String fields) {
        return (InsertSong) super.setFields(fields);
      }

      @Override
      public InsertSong setKey(java.lang.String key) {
        return (InsertSong) super.setKey(key);
      }

      @Override
      public InsertSong setOauthToken(java.lang.String oauthToken) {
        return (InsertSong) super.setOauthToken(oauthToken);
      }

      @Override
      public InsertSong setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (InsertSong) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public InsertSong setQuotaUser(java.lang.String quotaUser) {
        return (InsertSong) super.setQuotaUser(quotaUser);
      }

      @Override
      public InsertSong setUserIp(java.lang.String userIp) {
        return (InsertSong) super.setUserIp(userIp);
      }

      @Override
      public InsertSong set(String parameterName, Object value) {
        return (InsertSong) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "roomEndPoint.nearByRooms".
     *
     * This request holds the parameters needed by the ffMusicApi server.  After setting any optional
     * parameters, call the {@link NearByRooms#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.ffmusic.backend.ffMusicApi.model.User}
     * @return the request
     */
    public NearByRooms nearByRooms(com.ffmusic.backend.ffMusicApi.model.User content) throws java.io.IOException {
      NearByRooms result = new NearByRooms(content);
      initialize(result);
      return result;
    }

    public class NearByRooms extends FfMusicApiRequest<com.ffmusic.backend.ffMusicApi.model.RoomCollection> {

      private static final String REST_PATH = "nearByRooms";

      /**
       * Create a request for the method "roomEndPoint.nearByRooms".
       *
       * This request holds the parameters needed by the the ffMusicApi server.  After setting any
       * optional parameters, call the {@link NearByRooms#execute()} method to invoke the remote
       * operation. <p> {@link
       * NearByRooms#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.ffmusic.backend.ffMusicApi.model.User}
       * @since 1.13
       */
      protected NearByRooms(com.ffmusic.backend.ffMusicApi.model.User content) {
        super(FfMusicApi.this, "POST", REST_PATH, content, com.ffmusic.backend.ffMusicApi.model.RoomCollection.class);
      }

      @Override
      public NearByRooms setAlt(java.lang.String alt) {
        return (NearByRooms) super.setAlt(alt);
      }

      @Override
      public NearByRooms setFields(java.lang.String fields) {
        return (NearByRooms) super.setFields(fields);
      }

      @Override
      public NearByRooms setKey(java.lang.String key) {
        return (NearByRooms) super.setKey(key);
      }

      @Override
      public NearByRooms setOauthToken(java.lang.String oauthToken) {
        return (NearByRooms) super.setOauthToken(oauthToken);
      }

      @Override
      public NearByRooms setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (NearByRooms) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public NearByRooms setQuotaUser(java.lang.String quotaUser) {
        return (NearByRooms) super.setQuotaUser(quotaUser);
      }

      @Override
      public NearByRooms setUserIp(java.lang.String userIp) {
        return (NearByRooms) super.setUserIp(userIp);
      }

      @Override
      public NearByRooms set(String parameterName, Object value) {
        return (NearByRooms) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "roomEndPoint.querySongs".
     *
     * This request holds the parameters needed by the ffMusicApi server.  After setting any optional
     * parameters, call the {@link QuerySongs#execute()} method to invoke the remote operation.
     *
     * @param prefix
     * @return the request
     */
    public QuerySongs querySongs(java.lang.String prefix) throws java.io.IOException {
      QuerySongs result = new QuerySongs(prefix);
      initialize(result);
      return result;
    }

    public class QuerySongs extends FfMusicApiRequest<com.ffmusic.backend.ffMusicApi.model.RoomCollection> {

      private static final String REST_PATH = "querySongs/{prefix}";

      /**
       * Create a request for the method "roomEndPoint.querySongs".
       *
       * This request holds the parameters needed by the the ffMusicApi server.  After setting any
       * optional parameters, call the {@link QuerySongs#execute()} method to invoke the remote
       * operation. <p> {@link
       * QuerySongs#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param prefix
       * @since 1.13
       */
      protected QuerySongs(java.lang.String prefix) {
        super(FfMusicApi.this, "POST", REST_PATH, null, com.ffmusic.backend.ffMusicApi.model.RoomCollection.class);
        this.prefix = com.google.api.client.util.Preconditions.checkNotNull(prefix, "Required parameter prefix must be specified.");
      }

      @Override
      public QuerySongs setAlt(java.lang.String alt) {
        return (QuerySongs) super.setAlt(alt);
      }

      @Override
      public QuerySongs setFields(java.lang.String fields) {
        return (QuerySongs) super.setFields(fields);
      }

      @Override
      public QuerySongs setKey(java.lang.String key) {
        return (QuerySongs) super.setKey(key);
      }

      @Override
      public QuerySongs setOauthToken(java.lang.String oauthToken) {
        return (QuerySongs) super.setOauthToken(oauthToken);
      }

      @Override
      public QuerySongs setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (QuerySongs) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public QuerySongs setQuotaUser(java.lang.String quotaUser) {
        return (QuerySongs) super.setQuotaUser(quotaUser);
      }

      @Override
      public QuerySongs setUserIp(java.lang.String userIp) {
        return (QuerySongs) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private java.lang.String prefix;

      /**

       */
      public java.lang.String getPrefix() {
        return prefix;
      }

      public QuerySongs setPrefix(java.lang.String prefix) {
        this.prefix = prefix;
        return this;
      }

      @Override
      public QuerySongs set(String parameterName, Object value) {
        return (QuerySongs) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "roomEndPoint.roomsByUser".
     *
     * This request holds the parameters needed by the ffMusicApi server.  After setting any optional
     * parameters, call the {@link RoomsByUser#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.ffmusic.backend.ffMusicApi.model.User}
     * @return the request
     */
    public RoomsByUser roomsByUser(com.ffmusic.backend.ffMusicApi.model.User content) throws java.io.IOException {
      RoomsByUser result = new RoomsByUser(content);
      initialize(result);
      return result;
    }

    public class RoomsByUser extends FfMusicApiRequest<com.ffmusic.backend.ffMusicApi.model.RoomCollection> {

      private static final String REST_PATH = "roomsByUser";

      /**
       * Create a request for the method "roomEndPoint.roomsByUser".
       *
       * This request holds the parameters needed by the the ffMusicApi server.  After setting any
       * optional parameters, call the {@link RoomsByUser#execute()} method to invoke the remote
       * operation. <p> {@link
       * RoomsByUser#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.ffmusic.backend.ffMusicApi.model.User}
       * @since 1.13
       */
      protected RoomsByUser(com.ffmusic.backend.ffMusicApi.model.User content) {
        super(FfMusicApi.this, "POST", REST_PATH, content, com.ffmusic.backend.ffMusicApi.model.RoomCollection.class);
      }

      @Override
      public RoomsByUser setAlt(java.lang.String alt) {
        return (RoomsByUser) super.setAlt(alt);
      }

      @Override
      public RoomsByUser setFields(java.lang.String fields) {
        return (RoomsByUser) super.setFields(fields);
      }

      @Override
      public RoomsByUser setKey(java.lang.String key) {
        return (RoomsByUser) super.setKey(key);
      }

      @Override
      public RoomsByUser setOauthToken(java.lang.String oauthToken) {
        return (RoomsByUser) super.setOauthToken(oauthToken);
      }

      @Override
      public RoomsByUser setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (RoomsByUser) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public RoomsByUser setQuotaUser(java.lang.String quotaUser) {
        return (RoomsByUser) super.setQuotaUser(quotaUser);
      }

      @Override
      public RoomsByUser setUserIp(java.lang.String userIp) {
        return (RoomsByUser) super.setUserIp(userIp);
      }

      @Override
      public RoomsByUser set(String parameterName, Object value) {
        return (RoomsByUser) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "roomEndPoint.songRoom".
     *
     * This request holds the parameters needed by the ffMusicApi server.  After setting any optional
     * parameters, call the {@link SongRoom#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.ffmusic.backend.ffMusicApi.model.SongRoom}
     * @return the request
     */
    public SongRoom songRoom(com.ffmusic.backend.ffMusicApi.model.SongRoom content) throws java.io.IOException {
      SongRoom result = new SongRoom(content);
      initialize(result);
      return result;
    }

    public class SongRoom extends FfMusicApiRequest<com.ffmusic.backend.ffMusicApi.model.SongRoom> {

      private static final String REST_PATH = "songRoom";

      /**
       * Create a request for the method "roomEndPoint.songRoom".
       *
       * This request holds the parameters needed by the the ffMusicApi server.  After setting any
       * optional parameters, call the {@link SongRoom#execute()} method to invoke the remote operation.
       * <p> {@link
       * SongRoom#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.ffmusic.backend.ffMusicApi.model.SongRoom}
       * @since 1.13
       */
      protected SongRoom(com.ffmusic.backend.ffMusicApi.model.SongRoom content) {
        super(FfMusicApi.this, "POST", REST_PATH, content, com.ffmusic.backend.ffMusicApi.model.SongRoom.class);
      }

      @Override
      public SongRoom setAlt(java.lang.String alt) {
        return (SongRoom) super.setAlt(alt);
      }

      @Override
      public SongRoom setFields(java.lang.String fields) {
        return (SongRoom) super.setFields(fields);
      }

      @Override
      public SongRoom setKey(java.lang.String key) {
        return (SongRoom) super.setKey(key);
      }

      @Override
      public SongRoom setOauthToken(java.lang.String oauthToken) {
        return (SongRoom) super.setOauthToken(oauthToken);
      }

      @Override
      public SongRoom setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (SongRoom) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public SongRoom setQuotaUser(java.lang.String quotaUser) {
        return (SongRoom) super.setQuotaUser(quotaUser);
      }

      @Override
      public SongRoom setUserIp(java.lang.String userIp) {
        return (SongRoom) super.setUserIp(userIp);
      }

      @Override
      public SongRoom set(String parameterName, Object value) {
        return (SongRoom) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "roomEndPoint.songs".
     *
     * This request holds the parameters needed by the ffMusicApi server.  After setting any optional
     * parameters, call the {@link Songs#execute()} method to invoke the remote operation.
     *
     * @param idRoom
     * @return the request
     */
    public Songs songs(java.lang.Long idRoom) throws java.io.IOException {
      Songs result = new Songs(idRoom);
      initialize(result);
      return result;
    }

    public class Songs extends FfMusicApiRequest<com.ffmusic.backend.ffMusicApi.model.SongRoomCollection> {

      private static final String REST_PATH = "songs/{idRoom}";

      /**
       * Create a request for the method "roomEndPoint.songs".
       *
       * This request holds the parameters needed by the the ffMusicApi server.  After setting any
       * optional parameters, call the {@link Songs#execute()} method to invoke the remote operation.
       * <p> {@link
       * Songs#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
       * be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param idRoom
       * @since 1.13
       */
      protected Songs(java.lang.Long idRoom) {
        super(FfMusicApi.this, "POST", REST_PATH, null, com.ffmusic.backend.ffMusicApi.model.SongRoomCollection.class);
        this.idRoom = com.google.api.client.util.Preconditions.checkNotNull(idRoom, "Required parameter idRoom must be specified.");
      }

      @Override
      public Songs setAlt(java.lang.String alt) {
        return (Songs) super.setAlt(alt);
      }

      @Override
      public Songs setFields(java.lang.String fields) {
        return (Songs) super.setFields(fields);
      }

      @Override
      public Songs setKey(java.lang.String key) {
        return (Songs) super.setKey(key);
      }

      @Override
      public Songs setOauthToken(java.lang.String oauthToken) {
        return (Songs) super.setOauthToken(oauthToken);
      }

      @Override
      public Songs setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Songs) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Songs setQuotaUser(java.lang.String quotaUser) {
        return (Songs) super.setQuotaUser(quotaUser);
      }

      @Override
      public Songs setUserIp(java.lang.String userIp) {
        return (Songs) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private java.lang.Long idRoom;

      /**

       */
      public java.lang.Long getIdRoom() {
        return idRoom;
      }

      public Songs setIdRoom(java.lang.Long idRoom) {
        this.idRoom = idRoom;
        return this;
      }

      @Override
      public Songs set(String parameterName, Object value) {
        return (Songs) super.set(parameterName, value);
      }
    }

  }

  /**
   * An accessor for creating requests from the UserEndPoint collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code FfMusicApi ffMusicApi = new FfMusicApi(...);}
   *   {@code FfMusicApi.UserEndPoint.List request = ffMusicApi.userEndPoint().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public UserEndPoint userEndPoint() {
    return new UserEndPoint();
  }

  /**
   * The "userEndPoint" collection of methods.
   */
  public class UserEndPoint {

    /**
     * Create a request for the method "userEndPoint.getUserByEmail".
     *
     * This request holds the parameters needed by the ffMusicApi server.  After setting any optional
     * parameters, call the {@link GetUserByEmail#execute()} method to invoke the remote operation.
     *
     * @param email
     * @return the request
     */
    public GetUserByEmail getUserByEmail(java.lang.String email) throws java.io.IOException {
      GetUserByEmail result = new GetUserByEmail(email);
      initialize(result);
      return result;
    }

    public class GetUserByEmail extends FfMusicApiRequest<com.ffmusic.backend.ffMusicApi.model.User> {

      private static final String REST_PATH = "user/{email}";

      /**
       * Create a request for the method "userEndPoint.getUserByEmail".
       *
       * This request holds the parameters needed by the the ffMusicApi server.  After setting any
       * optional parameters, call the {@link GetUserByEmail#execute()} method to invoke the remote
       * operation. <p> {@link GetUserByEmail#initialize(com.google.api.client.googleapis.services.Abstr
       * actGoogleClientRequest)} must be called to initialize this instance immediately after invoking
       * the constructor. </p>
       *
       * @param email
       * @since 1.13
       */
      protected GetUserByEmail(java.lang.String email) {
        super(FfMusicApi.this, "GET", REST_PATH, null, com.ffmusic.backend.ffMusicApi.model.User.class);
        this.email = com.google.api.client.util.Preconditions.checkNotNull(email, "Required parameter email must be specified.");
      }

      @Override
      public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
        return super.executeUsingHead();
      }

      @Override
      public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
        return super.buildHttpRequestUsingHead();
      }

      @Override
      public GetUserByEmail setAlt(java.lang.String alt) {
        return (GetUserByEmail) super.setAlt(alt);
      }

      @Override
      public GetUserByEmail setFields(java.lang.String fields) {
        return (GetUserByEmail) super.setFields(fields);
      }

      @Override
      public GetUserByEmail setKey(java.lang.String key) {
        return (GetUserByEmail) super.setKey(key);
      }

      @Override
      public GetUserByEmail setOauthToken(java.lang.String oauthToken) {
        return (GetUserByEmail) super.setOauthToken(oauthToken);
      }

      @Override
      public GetUserByEmail setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (GetUserByEmail) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public GetUserByEmail setQuotaUser(java.lang.String quotaUser) {
        return (GetUserByEmail) super.setQuotaUser(quotaUser);
      }

      @Override
      public GetUserByEmail setUserIp(java.lang.String userIp) {
        return (GetUserByEmail) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private java.lang.String email;

      /**

       */
      public java.lang.String getEmail() {
        return email;
      }

      public GetUserByEmail setEmail(java.lang.String email) {
        this.email = email;
        return this;
      }

      @Override
      public GetUserByEmail set(String parameterName, Object value) {
        return (GetUserByEmail) super.set(parameterName, value);
      }
    }
    /**
     * Create a request for the method "userEndPoint.insertUser".
     *
     * This request holds the parameters needed by the ffMusicApi server.  After setting any optional
     * parameters, call the {@link InsertUser#execute()} method to invoke the remote operation.
     *
     * @param content the {@link com.ffmusic.backend.ffMusicApi.model.User}
     * @return the request
     */
    public InsertUser insertUser(com.ffmusic.backend.ffMusicApi.model.User content) throws java.io.IOException {
      InsertUser result = new InsertUser(content);
      initialize(result);
      return result;
    }

    public class InsertUser extends FfMusicApiRequest<com.ffmusic.backend.ffMusicApi.model.User> {

      private static final String REST_PATH = "user";

      /**
       * Create a request for the method "userEndPoint.insertUser".
       *
       * This request holds the parameters needed by the the ffMusicApi server.  After setting any
       * optional parameters, call the {@link InsertUser#execute()} method to invoke the remote
       * operation. <p> {@link
       * InsertUser#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
       * must be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.ffmusic.backend.ffMusicApi.model.User}
       * @since 1.13
       */
      protected InsertUser(com.ffmusic.backend.ffMusicApi.model.User content) {
        super(FfMusicApi.this, "POST", REST_PATH, content, com.ffmusic.backend.ffMusicApi.model.User.class);
      }

      @Override
      public InsertUser setAlt(java.lang.String alt) {
        return (InsertUser) super.setAlt(alt);
      }

      @Override
      public InsertUser setFields(java.lang.String fields) {
        return (InsertUser) super.setFields(fields);
      }

      @Override
      public InsertUser setKey(java.lang.String key) {
        return (InsertUser) super.setKey(key);
      }

      @Override
      public InsertUser setOauthToken(java.lang.String oauthToken) {
        return (InsertUser) super.setOauthToken(oauthToken);
      }

      @Override
      public InsertUser setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (InsertUser) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public InsertUser setQuotaUser(java.lang.String quotaUser) {
        return (InsertUser) super.setQuotaUser(quotaUser);
      }

      @Override
      public InsertUser setUserIp(java.lang.String userIp) {
        return (InsertUser) super.setUserIp(userIp);
      }

      @Override
      public InsertUser set(String parameterName, Object value) {
        return (InsertUser) super.set(parameterName, value);
      }
    }

  }

  /**
   * Builder for {@link FfMusicApi}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link FfMusicApi}. */
    @Override
    public FfMusicApi build() {
      return new FfMusicApi(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link FfMusicApiRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setFfMusicApiRequestInitializer(
        FfMusicApiRequestInitializer ffmusicapiRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(ffmusicapiRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}

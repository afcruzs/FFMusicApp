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
 * on 2015-10-11 at 17:01:45 UTC 
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

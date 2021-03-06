package com.example.megha.movieplate.utility.API;

import com.example.megha.movieplate.CelebsFormat.Celebs;
import com.example.megha.movieplate.CelebsFormat.CelebsDetails;
import com.example.megha.movieplate.MovieFormat.MovieList;
import com.example.megha.movieplate.SignInPackage.AccountDetails;
import com.example.megha.movieplate.SignInPackage.account_access;
import com.example.megha.movieplate.SignInPackage.session_id;
import com.example.megha.movieplate.TVFormat.TVList;
import com.example.megha.movieplate.WatchlistFormat.PostJsonInWatchList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by megha on 14/08/17.
 */

public interface MovieDBApiInterface {

    @GET("/3/authentication/token/new")
    Call<account_access> getRequestToken(@Query("api_key") String key);

    @GET("/3/authentication/token/validate_with_login")
    Call<account_access> getRequestAuthenticated(@Query("api_key") String key,@Query("request_token") String request_token,@Query("username") String username,@Query("password") String password);

    @GET("/3/authentication/session/new")
    Call<session_id> getSessionID(@Query("api_key") String key, @Query("request_token") String request_token);

    @GET("/3/account")
    Call<AccountDetails> getAccountDetails(@Query("api_key") String key, @Query("session_id") String session_id);

    @GET("/3/movie/{type}")
    Call<MovieList> getMovies(@Path("type") String type, @Query("api_key") String key);

    @GET("/3/tv/{type}")
    Call<TVList> getTVShows(@Path("type") String type, @Query("api_key") String key);

    @GET("/3/person/popular")
    Call<Celebs> getPopularPerson(@Query("api_key") String key);

    @GET("/3/person/{id}")
    Call<CelebsDetails> getPersonDetails(@Path("id") String userId, @Query("api_key") String key);

    @GET("/3/account/{id}/watchlist/movies")
    Call<MovieList> getUserMovieWatchlist(@Path("id") String user_id, @Query("api_key") String key, @Query("session_id") String session_id);

    @GET("/3/account/{id}/watchlist/tv")
    Call<TVList> getUserTVShowWatchlist(@Path("id") String user_id, @Query("api_key") String key, @Query("session_id") String session_id);

    @POST("/3/account/{id}/watchlist")
    Call<PostJsonInWatchList> createJson(@Path("id") String user_id, @Query("api_key") String key, @Query("session_id") String session_id, @Body PostJsonInWatchList postJsonInWatchList);

}

package http;


import java.util.List;

import http.entity.HttpResult;
import http.entity.Subject;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liukun on 16/3/9.
 */
public interface MovieService {

//    @GET("top250")
//    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

//    @GET("top250")
//    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

//    @GET("top250")
//    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
       /*test get*/
    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
  /*test Post  @part*/
  /*  @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Part("start") int start, @Part("count") int count);*/
    /*test post @Field*/
     /* @FormUrlEncoded
      @POST("top250")
      Observable<HttpResult<List<Subject>>> getTopMovie(@Field("start") int start, @Field("count") int count);*/

}

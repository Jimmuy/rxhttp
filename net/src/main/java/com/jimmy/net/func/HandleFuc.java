

package com.jimmy.net.func;



import com.jimmy.net.exception.ApiException;
import com.jimmy.net.exception.ServerException;
import com.jimmy.net.model.ApiResult;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * <p>描述：ApiResult<T>转换T</p>
 */
public class HandleFuc<T> implements Function<ApiResult<T>, T> {
    @Override
    public T apply(@NonNull ApiResult<T> tApiResult) throws Exception {
        if (ApiException.isOk(tApiResult)) {
            return tApiResult.getBaseData();// == null ? Optional.ofNullable(tApiResult.getBaseData()).orElse(null) : tApiResult.getBaseData();
        } else {
            throw new ServerException(tApiResult.getCode(), tApiResult.getMsg());
        }
    }
}

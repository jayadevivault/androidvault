/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.owncloud.android.vo

import com.owncloud.android.lib.common.operations.RemoteOperationResult.ResultCode
import com.owncloud.android.vo.Status.ERROR
import com.owncloud.android.vo.Status.LOADING
import com.owncloud.android.vo.Status.SUCCESS

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(
    val status: Status,
    val code: ResultCode? = null,
    val data: T? = null,
    val msg: String? = null,
    val exception: Exception? = null
) {
    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(SUCCESS, ResultCode.OK, data)
        }

        fun <T> error(
            code: ResultCode? = null,
            data: T? = null,
            msg: String? = null,
            exception: Exception? = null
        ): Resource<T> {
            return Resource(ERROR, code, data, msg, exception)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(LOADING, data = data)
        }
    }

    fun isSuccess(): Boolean = code?.equals(ResultCode.OK) ?: false
}

package test1.entity

import com.google.gson.annotations.SerializedName

data class AuthorsData(val page: Int,
                       @SerializedName("total_pages")
                       val totalPages: Int,
                       @SerializedName("data")
                       val authors: List<Author>)

data class Author(val id: Int,
                  @SerializedName("username")
                  val userName: String,
                  @SerializedName("submission_count")
                  val submissionCount: Int,
                  @SerializedName("comment_count")
                  val commentCount: Int,
                  @SerializedName("created_at")
                  val createdAt: Int)
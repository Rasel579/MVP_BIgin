package com.test_app.mvp_bigin.model.storage

import com.test_app.mvp_bigin.model.retrofit.GithubRepos
import com.test_app.mvp_bigin.model.retrofit.GithubUser
import com.test_app.mvp_bigin.utils.schedulers.Schedulers
import io.reactivex.rxjava3.core.Single

class RoomRepositoryImpl(private val db: RoomDB, private val schedulers: Schedulers) : Storage {
    override fun insertUsers(users: List<GithubUser>) {
        val roomUsers = users.map { user ->
            RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
        }
        db.userDao.insert(roomUsers)
    }

    override fun insertGithubRepos(repos: List<GithubRepos>, usersUrl: String?) {
        val roomRepos = repos.map { repo ->
            RoomGithubRepo(
                repo.id,
                repo.name,
                repo.date,
                repo.language,
                repo.forks,
                usersUrl,
                repo.url
            )
        }
        db.repoDao.insert(roomRepos)
    }

    override fun getUsers(): Single<List<GithubUser>> = Single.fromCallable {
        db.userDao.getAll().map {
            GithubUser(it.id, it.login, it.avatarUrl, it.reposUrl)
        }
    }.subscribeOn(schedulers.background())

    override fun getRepos(url: String?): Single<List<GithubRepos>> = Single.fromCallable {
        db.repoDao.findForUserRepo(url).map { repo ->
            GithubRepos(repo.id, repo.name, repo.date, repo.language, repo.forksCount, repo.url)
        }
    }

    override fun getRepo(url: String?): Single<GithubRepos> = Single.fromCallable {
        db.repoDao.findRepo(url).let {
            GithubRepos(it.id, it.name, it.date, it.language, it.forksCount, it.url)
        }
    }
}




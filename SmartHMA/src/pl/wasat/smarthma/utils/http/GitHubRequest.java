package pl.wasat.smarthma.utils.http;

import pl.wasat.smarthma.model.GitHubUser;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class GitHubRequest extends SpringAndroidSpiceRequest<GitHubUser.List> {

    private String keyword;

    public GitHubRequest(String keyword) {
        super(GitHubUser.List.class);
        this.keyword = keyword;
    }

    @Override
    public GitHubUser.List loadDataFromNetwork() throws Exception {
        String url = "https://api.github.com/legacy/user/search/" + keyword;
        return getRestTemplate().getForObject(url, GitHubUser.List.class);
    }

}

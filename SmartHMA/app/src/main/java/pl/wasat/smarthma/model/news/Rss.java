package pl.wasat.smarthma.model.news;

/**
 * Created by Daniel on 2016-03-10.
 * This file is a part of module SmartHMA project.
 */
public class Rss {
    private static final long serialVersionUID = 1L;

    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}

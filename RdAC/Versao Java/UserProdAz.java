package Corpo;

import android.os.Parcel;
import android.os.Parcelable;

public class UserProdAz implements Parcelable {

    private String uuid;
    private String username;
    private String zone;
    private String localidade;
    private String phone;
    private String emailto;
    private String profileUrl;

    public UserProdAz() {

    }

    public UserProdAz(String uuid, String username, String zone, String localidade,
            String phone, String emailto, String profileUrl) {
        this.uuid = uuid;
        this.username = username;
        this.zone = zone;
        this.localidade = localidade;
        this.phone = phone;
        this.emailto = emailto;
        this.profileUrl = profileUrl;
    }

    protected UserProdAz(Parcel in) {
        uuid = in.readString();
        username = in.readString();
        zone = in.readString();
        localidade = in.readString();
        phone = in.readString();
        emailto = in.readString();
        profileUrl = in.readString();
    }

    public static final Creator<UserProdAz> CREATOR = new Creator<UserProdAz>() {
        @Override
        public UserProdAz createFromParcel(Parcel in) {
            return new UserProdAz(in);
        }

        @Override
        public UserProdAz[] newArray(int size) {
            return new UserProdAz[size];
        }
    };

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getZone() {
        return zone;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmailto() {
        return emailto;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(username);
        dest.writeString(zone);
        dest.writeString(localidade);
        dest.writeString(phone);
        dest.writeString(emailto);
        dest.writeString(profileUrl);
    }
}
package com.example.iventorypurilupin.response.response_ks_whole;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Response_ks_whole {

	@SerializedName("barang")
	private List<Ks_WholeItem> barang;

	@SerializedName("status")
	private boolean status;

	public void setBarang(List<Ks_WholeItem> barang){
		this.barang = barang;
	}

	public List<Ks_WholeItem> getBarang(){
		return barang;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Response_ks_whole{" +
			"barang = '" + barang + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
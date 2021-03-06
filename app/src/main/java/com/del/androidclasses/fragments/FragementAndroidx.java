package com.del.androidclasses.fragments;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.del.androidclasses.R;
import android.widget.ListView;
import com.del.androidclasses.read.AsssetTask;
import com.del.androidclasses.read.ResultTask;
import java.util.List;
import com.del.androidclasses.model.ClassesModel;
import com.del.androidclasses.adapters.ListviewAndroidxAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;
import android.content.*;
import android.net.*;

public class FragementAndroidx extends Fragment {
	
	private ListView listView;
	private ListviewAndroidxAdapter adapter;
	
	public FragementAndroidx(){
		
	}
    
	public static FragementAndroidx newInstance(){
		FragementAndroidx fragmentAndroidx = new FragementAndroidx();
		Bundle bundle = new Bundle();
		fragmentAndroidx.setArguments(bundle);
		return fragmentAndroidx;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_androidx, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		initLogic();
	}

	private void initView() {
		listView = (ListView)getActivity().findViewById(R.id.listView);
	}
	
	private void initLogic(){
		AsssetTask ass = new AsssetTask(getActivity(), new ResultTask(){

				@Override
				public void onFinisedTask(List<ClassesModel> listResult) {
					adapter = new ListviewAndroidxAdapter(getActivity(), listResult);
					listView.setAdapter(adapter);
				}
			});
			ass.execute();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_search, menu);
		MenuItem menuItem = menu.findItem(R.id.action_search);
		SearchView searchView = (SearchView)menuItem.getActionView();
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

				@Override
				public boolean onQueryTextSubmit(String charSeq) {
					
					return false;
				}

				@Override
				public boolean onQueryTextChange(String charSeq) {
					if(charSeq != null){
						adapter.getFilter().filter(charSeq);
					}
					return false;
				}
			});
		
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.item:
				startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://github.com/ghodel-dev")));
			break;
			default:
			return true;
		}
	
		
		return super.onOptionsItemSelected(item);
	}
}

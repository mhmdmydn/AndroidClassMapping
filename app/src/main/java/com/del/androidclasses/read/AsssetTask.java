package com.del.androidclasses.read;
import android.os.AsyncTask;
import java.util.List;
import com.del.androidclasses.model.ClassesModel;
import android.app.ProgressDialog;
import android.content.Context;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;
import android.widget.Toast;

public class AsssetTask extends AsyncTask<Void, Integer, List<ClassesModel>> {
	
	private ProgressDialog prog;
	private Context context;
	private ResultTask resultTask;
	
	public AsssetTask(Context context, ResultTask resultTask){
		this.context = context;
		this.resultTask = resultTask;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		prog = new ProgressDialog(context);
        prog.setTitle("Loading json assets");
        prog.setMessage("Please Wait....");
        prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prog.setCancelable(false);
        prog.setCanceledOnTouchOutside(false);
        prog.show();
	}
	
	@Override
	protected List<ClassesModel> doInBackground(Void... params) {
		List<ClassesModel> lcm = new ArrayList<>();
		
		try {
			JSONArray m_jArry = new JSONArray(loadJSONFromAsset());
			for (int i = 0; i < m_jArry.length(); i++) {
				JSONObject obj = m_jArry.getJSONObject(i);
				String support = obj.getString("Support Library class");
				String androidx = obj.getString("Android X class");
				
				ClassesModel cm = new ClassesModel();
				cm.setText(support);
				cm.setText2(androidx);
				lcm.add(cm);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return lcm;
	}

	@Override
	protected void onPostExecute(List<ClassesModel> result) {
		super.onPostExecute(result);
		if(prog != null && prog.isShowing()){
			prog.dismiss();
		resultTask.onFinisedTask(result);
		}
	}
	
	public String loadJSONFromAsset() {
		String json = null;
		try {
			InputStream is = context.getAssets().open("classes.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}
	
}

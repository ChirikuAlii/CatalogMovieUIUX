package id.chirikualii.catalogmovieuiux.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.google.gson.Gson;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import id.chirikualii.catalogmovieuiux.R;
import id.chirikualii.catalogmovieuiux.data.ApiService;
import id.chirikualii.catalogmovieuiux.data.repo.UpcomingRepo;
import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;
import id.chirikualii.catalogmovieuiux.presentasion.ui.detailmovie.DetailMovieActivity;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchedulerService extends GcmTaskService{

    public static String TAG_TASK_UPCOMING = "upcoming movies";
    @Inject
    UpcomingRepo repo;



    //private Call<UpcomingModel> apiCall;
    //private APIClient apiClient = new APIClient();

    @Override
    public int onRunTask(TaskParams taskParams) {
        int result = 0;
        if (taskParams.getTag().equals(TAG_TASK_UPCOMING)) {
            //loadData();
            if(repo!=null){
                repo.loadUpcomingList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                it ->{
                                    List<Movie> items = it;
                                    int index = new Random().nextInt(items.size());
                                    Movie item = items.get(index);
                                    String title = items.get(index).getOriginalTitle();
                                    String message = items.get(index).getOverview();
                                    int notifId = 200;
                                    showNotification(getApplicationContext(),title,message,notifId,item);
                                },
                                it->{
                                    Toast.makeText(this, "Failed to load data.\\nPlease check your Internet connections!", Toast.LENGTH_SHORT).show();
                                }
                        ).isDisposed();

            }
            result = GcmNetworkManager.RESULT_SUCCESS;
        }

        return result;
    }

    /*private void loadData() {
        apiCall = apiClient.getService().getUpcomingMovie(Language.getCountry());
        apiCall.enqueue(new Callback<UpcomingModel>() {
            @Override
            public void onResponse(Call<UpcomingModel> call, Response<UpcomingModel> response) {
                if (response.isSuccessful()) {
                    List<ResultsItem> items = response.body().getResults();
                    int index = new Random().nextInt(items.size());

                    ResultsItem item = items.get(index);
                    String title = items.get(index).getTitle();
                    String message = items.get(index).getOverview();
                    int notifId = 200;

                    showNotification(getApplicationContext(), title, message, notifId, item);

                } else loadFailed();
            }

            @Override
            public void onFailure(Call<UpcomingModel> call, Throwable t) {
                loadFailed();
            }
        });
    }*/

    /*private void loadFailed() {
        Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show();
    }*/

    private void showNotification(Context context, String title, String message, int notifId, Movie item) {
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(context, DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.DETAIL_MOVIE_KEY, item);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notifId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound);

        notificationManagerCompat.notify(notifId, builder.build());
    }
}

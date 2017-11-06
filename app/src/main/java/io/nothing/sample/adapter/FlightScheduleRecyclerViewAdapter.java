package io.nothing.sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import io.nothing.sample.R;
import io.nothing.sample.pojo.FlightSchedule;

/**
 * Created by AangJnr on 8/10/16.
 */
public class FlightScheduleRecyclerViewAdapter extends RecyclerView.Adapter<FlightScheduleRecyclerViewAdapter.ViewHolder> {

    private static final int ANIMATED_ITEMS_COUNT = 2;
    static OnItemClickListener mItemClickListener;
    List<FlightSchedule> flightSchedules;
    private Context context;
    private int lastAnimatedPosition = -1;

    /**
     * Constructor
     *
     * @param options
     **/

    public FlightScheduleRecyclerViewAdapter(Context context, List<FlightSchedule> options) {
        this.flightSchedules = options;
        this.context = context;

    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return flightSchedules.size();

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.flight_schedule_item_view, viewGroup, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        FlightSchedule flightSchedule = flightSchedules.get(position);

        viewHolder.departure.setText(flightSchedule.getDeparture());
        viewHolder.departureTime.setText(flightSchedule.getDepartureTime());
        viewHolder.departureDays.setText(flightSchedule.getDepartureDays());
        viewHolder.departureNameCode.setText(flightSchedule.getDepartureNameCode());

        viewHolder.destination.setText(flightSchedule.getArrival());
        viewHolder.destinationTime.setText(flightSchedule.getArrivalTime());
        viewHolder.destinationDays.setText(flightSchedule.getArrivalDays());
        viewHolder.destinationNameCode.setText(flightSchedule.getArrivalNameCode());


    }


    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        FlightScheduleRecyclerViewAdapter.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout mainLayout;
        TextView departure;
        TextView departureTime;
        TextView departureDays;
        TextView departureNameCode;

        TextView destination;
        TextView destinationTime;
        TextView destinationDays;
        TextView destinationNameCode;


        ViewHolder(View itemView) {
            super(itemView);


            mainLayout = (LinearLayout) itemView.findViewById(R.id.mainLayout);
            departure = (TextView) itemView.findViewById(R.id.departure);
            departureTime = (TextView) itemView.findViewById(R.id.departureTime);
            departureDays = (TextView) itemView.findViewById(R.id.departureDays);
            departureNameCode = (TextView) itemView.findViewById(R.id.departureNameCode);

            destination = (TextView) itemView.findViewById(R.id.destination);
            destinationTime = (TextView) itemView.findViewById(R.id.destinationTime);
            destinationDays = (TextView) itemView.findViewById(R.id.destinationDays);
            destinationNameCode = (TextView) itemView.findViewById(R.id.destinationNameCode);

            mainLayout.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getAdapterPosition());


            }

        }

    }


}



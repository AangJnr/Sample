package io.nothing.sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.nothing.sample.R;
import io.nothing.sample.pojo.FlightStatus;

/**
 * Created by AangJnr on 8/10/16.
 */
public class FlightStatusRecyclerViewAdapter extends RecyclerView.Adapter<FlightStatusRecyclerViewAdapter.ViewHolder> {

    private static final int ANIMATED_ITEMS_COUNT = 2;
    static OnItemClickListener mItemClickListener;
    List<FlightStatus> flightStatuses;
    private Context context;
    private int lastAnimatedPosition = -1;

    /**
     * Constructor
     *
     * @param options
     **/

    public FlightStatusRecyclerViewAdapter(Context context, List<FlightStatus> options) {
        this.flightStatuses = options;
        this.context = context;

    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return flightStatuses.size();

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.flight_status_item_view, viewGroup, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        FlightStatus flightStatus = flightStatuses.get(position);

        viewHolder.flightNo.setText(flightStatus.getFlightNo());
        viewHolder.date.setText(flightStatus.getDate());
        viewHolder.source.setText(flightStatus.getSource());
        viewHolder.departureTime.setText(flightStatus.getDepartureTime());
        viewHolder.destination.setText(flightStatus.getDestination());
        viewHolder.arrivalTime.setText(flightStatus.getArrivalTime());
        viewHolder.flightTime.setText(flightStatus.getFlightTime());
        viewHolder.expectedIn.setText(flightStatus.getExpectedIn());


    }


    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        FlightStatusRecyclerViewAdapter.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView flightNo;
        TextView date;
        TextView source;
        TextView departureTime;
        TextView destination;
        TextView arrivalTime;
        TextView flightTime;
        TextView expectedIn;


        ViewHolder(View itemView) {
            super(itemView);

            flightNo = (TextView) itemView.findViewById(R.id.flightNo);
            date = (TextView) itemView.findViewById(R.id.date);
            source = (TextView) itemView.findViewById(R.id.source);
            departureTime = (TextView) itemView.findViewById(R.id.departureTime);
            destination = (TextView) itemView.findViewById(R.id.destination);
            arrivalTime = (TextView) itemView.findViewById(R.id.arrivalTime);
            flightTime = (TextView) itemView.findViewById(R.id.flightTime);
            expectedIn = (TextView) itemView.findViewById(R.id.expectedIn);


        }


        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getAdapterPosition());


            }

        }

    }


}



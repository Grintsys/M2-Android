package grintsys.com.vanshop.ux.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

import grintsys.com.vanshop.R;
import grintsys.com.vanshop.views.TouchImageView;

/**
 * Simple images pager adapter. Uses {@link grintsys.com.vanshop.views.TouchImageView} for zooming single images.
 */
public class ProductImagesPagerAdapter extends PagerAdapter {
    private Context context;
    private List<String> images;

    /**
     * Creates an adapter for viewing product images.
     *
     * @param context activity context.
     * @param images  list of product images.
     */
    public ProductImagesPagerAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return this.images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TouchImageView imgDisplay;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container, false);
        imgDisplay = (TouchImageView) viewLayout.findViewById(R.id.fullscreen_image);

        Picasso.with(context).load(images.get(position))
                .fit().centerInside()
                .placeholder(R.drawable.placeholder_loading)
                .error(R.drawable.placeholder_error)
                .into(imgDisplay);

        container.addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
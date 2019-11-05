package pl.kapiz.yourfirehouse.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_map.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController.Visibility.SHOW_AND_FADEOUT
import pl.kapiz.yourfirehouse.R
import pl.kapiz.yourfirehouse.base.BaseFragment
import javax.inject.Inject

class MapFragment : BaseFragment<MapPresenter>(), MapView {

    @Inject
    override lateinit var presenter: MapPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onAttachView(this, context)
    }

    override fun initView() {
        /* mapView.setTileSource(object : OnlineTileSourceBase(
            "Abakus",
            6,
            18,
            256,
            ".png",
            arrayOf("https://mapa.abakus.net.pl/tiles/base/")
        ) {
            override fun getTileURLString(pMapTileIndex: Long): String {
                return baseUrl +
                        MapTileIndex.getZoom(pMapTileIndex) + '/' +
                        MapTileIndex.getX(pMapTileIndex) + '/' +
                        MapTileIndex.getY(pMapTileIndex) + mImageFilenameEnding +
                        "?KeyID=gai3eighie9eithahquor7eesi3pho5ahfohPod6"
            }
        }) */

        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.zoomController.setVisibility(SHOW_AND_FADEOUT)
        mapView.setMultiTouchControls(true)
    }

    override fun setPosition(latitude: Double, longitude: Double, zoom: Double?) {
        mapView.controller.apply {
            zoom?.let { setZoom(it) }
            setCenter(GeoPoint(latitude, longitude))
        }
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }
}

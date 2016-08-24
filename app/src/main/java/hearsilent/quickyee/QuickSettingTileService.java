package hearsilent.quickyee;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.widget.Toast;

public class QuickSettingTileService extends TileService {

	private SoundPool mSoundPool;
	private int mYeeSound = -1;

	private int getYeeSound() {
		if (mYeeSound > -1) {
			return mYeeSound;
		} else {
			if (mSoundPool == null) {
				setUpSoundPool();
			}
			mYeeSound = mSoundPool.load(this, R.raw.yee, 1);
			return mYeeSound;
		}
	}

	private void setUpSoundPool() {
		mSoundPool = new SoundPool.Builder().setAudioAttributes(
				new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION)
						.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()).build();
	}

	private void playYee() {
		if (mSoundPool == null) {
			setUpSoundPool();
		}
		mSoundPool.play(getYeeSound(), 1.0f, 1.0f, 0, 0, 1.0f);
		Toast.makeText(this, R.string.app_name, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onStartListening() {
		super.onStartListening();
		getQsTile().setState(Tile.STATE_ACTIVE);
		getQsTile().updateTile();
	}

	@Override
	public void onClick() {
		super.onClick();
		playYee();
	}

}
package peyto.ide.editors.message.dnd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TransferData;

import peyto.ide.core.data.MessageFieldDto;
import peyto.ide.editors.message.ui.MessageFieldTreeElement;

public class MessageFieldDtoDragAndDropTransfer extends ByteArrayTransfer {

	private static final String TYPE_NAME = MessageFieldDtoDragAndDropTransfer.class.getSimpleName();

	private static final int TYPE_ID = registerType( TYPE_NAME );
	
	private static MessageFieldDtoDragAndDropTransfer INSTANCE = new MessageFieldDtoDragAndDropTransfer();
	
	public static MessageFieldDtoDragAndDropTransfer getInstance() {
		return INSTANCE;
	}
	
	@Override
	protected int[] getTypeIds() {
		return new int[] { TYPE_ID };
	}

	@Override
	protected String[] getTypeNames() {
		return new String[] { TYPE_NAME };
	}

	@Override
	protected Object nativeToJava(TransferData transferData) {
		System.out.println("MessageFieldDtoDragAndDropTransfer::nativeToJava");
		if ( isSupportedType( transferData ) ) {
			byte[] rawData = (byte[]) super.nativeToJava( transferData );
			if ( rawData == null ) {
				return null;
			}
			MessageFieldTreeElement<MessageFieldDto>[] items = null;
			try ( ByteArrayInputStream bais = new ByteArrayInputStream( rawData ) ) {
				try ( ObjectInputStream ois = new ObjectInputStream( bais ) ) {
					Object transferedObj = ois.readObject();
					items = (MessageFieldTreeElement<MessageFieldDto>[]) transferedObj;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} catch ( IOException ex ) {
				ex.printStackTrace();
			}
			return items;
		} else {
			return null;
		}
	}
	
	@Override
	protected void javaToNative(Object object, TransferData transferData) {
		System.out.println("MessageFieldDtoDragAndDropTransfer::javaToNative");
		if ( !validate( object ) || !isSupportedType( transferData ) ) {
			DND.error( DND.ERROR_INVALID_DATA );
		}
		MessageFieldTreeElement<MessageFieldDto>[] item = (MessageFieldTreeElement<MessageFieldDto>[]) object;
		byte[]	rawData = null;
		try ( ByteArrayOutputStream baos = new ByteArrayOutputStream() ) {
			try ( ObjectOutputStream oos = new ObjectOutputStream( baos ) ) {
				oos.writeObject( item );
				rawData	= baos.toByteArray();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.javaToNative(rawData, transferData);
	}

}
